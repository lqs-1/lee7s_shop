package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.config.EmailCodeClient;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.entity.Goods;
import com.lee7s.shop.back.entity.Order;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.mapper.OrderMapper;
import com.lee7s.shop.back.mq.publisher.OrderAutoCancelPublisher;
import com.lee7s.shop.back.service.GoodsService;
import com.lee7s.shop.back.service.OrderService;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.utils.MessageDigestHexUtil;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.vo.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderAutoCancelPublisher<Order> orderAutoCancelPublisher;

    @Autowired
    private EmailCodeClient emailCodeClient;


    /**
     * 构造订单 alipay
     *
     * @param orderPayVo
     * @return
     */

    @Override
    public PayVo constructOrderPayDataAliPay(OrderPayVo orderPayVo) {

        System.out.println(orderPayVo);
        PayVo payVo = new PayVo();
        payVo.setOut_trade_no(IdWorker.get32UUID());
        payVo.setSubject(orderPayVo.getProductName());
        payVo.setTotal_amount(orderPayVo.getGoodsPrice().toString());
        payVo.setOrderDesc(orderPayVo.getProductName());
        payVo.setTimeout_express("1m");
        return payVo;

    }

    /**
     * 构造订单 V免签支付
     *
     * @param orderPayVo
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public VPayVo constructOrderPayDataVPay(OrderPayVo orderPayVo) {

        String orderSn = IdWorker.get32UUID();

        // 填充订单信息
        Order order = new Order();
        order.setOrderSn(orderSn);
        order.setOrderStatus(Constant.OrderStatus.LOCK.getStatusCode());
        order.setOrderTotalPrice(orderPayVo.getOrderTotalPrice());
        order.setGoodsPrice(orderPayVo.getGoodsPrice());
        order.setEmail(orderPayVo.getEmail());
        order.setGoodsNum(orderPayVo.getGoodsNum());
        order.setProductName(orderPayVo.getProductName());
        order.setProductId(orderPayVo.getProductId());

        // 判断是否是无库存产品 无库存产品的具体实现不在商品逻辑中 也不需要扣减库存 修改状态
        if (orderPayVo.getType() == Constant.ProductType.HAS_STOCK.getStatusCode()) {
            // 修改商品状态
            List<Integer> lockGoodsIdList = goodsService.lockGoodsByProductId(orderPayVo.getProductId(), orderPayVo.getGoodsNum());

            if (lockGoodsIdList.size() == orderPayVo.getGoodsNum()) {
                // 产品锁定库存修改
                productService.localProductStock(orderPayVo.getProductId(), orderPayVo.getGoodsNum());

            }

            StringBuffer goodsDetailIds = new StringBuffer();
            for (Integer goodsId : lockGoodsIdList) {
                goodsDetailIds.append(goodsId);
                if (lockGoodsIdList.get(orderPayVo.getGoodsNum() - 1) != goodsId) {
                    goodsDetailIds.append(":");
                }
            }

            order.setGoodsDetailIds(goodsDetailIds.toString());
        }

        // TODO 发送到延时队列
        // 75.127.13.112
        orderAutoCancelPublisher.sendOrderCancelTimeDelayMessage(order);

        // 保存订单
        this.baseMapper.insert(order);


        // 填充支付对象
        VPayVo vPayVo = new VPayVo();
        vPayVo.setOut_trade_no(orderSn);
        vPayVo.setPayMethod(orderPayVo.getPayMethod());
        vPayVo.setTotal_amount(String.valueOf(orderPayVo.getOrderTotalPrice()));
        vPayVo.setParam(orderSn);
        vPayVo.setSign(MessageDigestHexUtil.doDigest(
                vPayVo.getOut_trade_no() +
                        vPayVo.getParam() +
                        vPayVo.getPayMethod() +
                        vPayVo.getTotal_amount() +
                        Constant.VKey
        ).getStrCode());
        vPayVo.setIsHtml("0");
        vPayVo.setNotifyUrl(Constant.NOTIFY_URL);
        vPayVo.setReturnUrl(Constant.RETURN_URL);


        return vPayVo;

    }

    /**
     * 获取订单分页数据
     *
     * @param param
     * @return
     */
    @Override
    public PageUtils requestOrderPageList(Map<String, Object> param) {

        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<Order>();

        if (!ObjectUtils.isEmpty(param.get("status"))) {
            queryWrapper.eq(Order::getOrderStatus, Integer.parseInt((String) param.get("status")));
        }

        IPage<Order> page = this.page(new QueryPage<Order>().getPage(param, true), queryWrapper);

        return new PageUtils(page);

    }

    /**
     * 自动取消订单 超时未支付
     *
     * @param order
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void orderAutoCancel(Order order) {

        String orderSn = order.getOrderSn();
        Order originOrder = this.baseMapper.selectById(orderSn);
        // 如果还是锁定状态就走这里 解锁库存 修改状态
        if (originOrder.getOrderStatus() == Constant.OrderStatus.LOCK.getStatusCode()) {

            // 获取订单产品 并判断产品是否是 无库存产品
            Product product = productService.requestProductById(order.getProductId());

            if (product.getType() == Constant.ProductType.HAS_STOCK.getStatusCode()) {
                // 解除商品的商品状态和上架状态
                String goodsIds = order.getGoodsDetailIds();

                List<Integer> goodsIdList = getGoodsIdList(goodsIds);

                goodsService.recoverGoodsStatusByIds(goodsIdList);

                // 恢复库存 扣除锁定库存
                Integer goodsNum = order.getGoodsNum();
                productService.recoverProductLockStock(order.getProductId(), goodsNum);
            }

            // 修改订单状态
            order.setOrderStatus(Constant.OrderStatus.CANCEL.getStatusCode());

            this.baseMapper.updateById(order);

        }

    }


    /**
     * 根据订单商品id字符串 处理成字符串列表
     *
     * @param goodsIds
     * @return
     */
    @NotNull
    private static List<Integer> getGoodsIdList(String goodsIds) {
        List<Integer> goodsIdList = new ArrayList();
        for (String goodsIdStr : goodsIds.split(":")) {
            goodsIdList.add(Integer.parseInt(goodsIdStr));
        }
        return goodsIdList;
    }

    /**
     * 订单完成 发送邮件 修改订单状态
     *
     * @param orderSn
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void completeOrder(String orderSn) {

        // 获取到订单
        Order order = this.baseMapper.selectById(orderSn);

        Product product = productService.requestProductById(order.getProductId());

        // 规定有库存的都是自动发货 没有库存的可以设置为手动发货

        // 判断产品是不是无库存产品
        if (product.getType() == Constant.ProductType.HAS_STOCK.getStatusCode()) {

            List<Integer> goodsIdList = getGoodsIdList(order.getGoodsDetailIds());

            // 修改商品状态 并下架商品 然后获取商品发送邮件
            List<Goods> saleGoodsList = goodsService.alterGoodsGoodsStatusToOFF(goodsIdList);

            // 扣减库存
            productService.deductionStock(order.getProductId(), order.getGoodsNum());

            // 发送邮件 TODO
            String email = order.getEmail();
            emailCodeClient.sendEmailOrder(email, order, saleGoodsList, 1);
        } else {
            // 如果是没有库存且是手动发货
            if (product.getProductMethod() == Constant.ProductMethod.MANUAL.getStatusCode()) {
                // 发邮件给客服
                emailCodeClient.manualProductSendToAssistant(order, 1);
                // 发邮件给客户
                emailCodeClient.manualProductSendToCustom(order, 1);
            } else {
                // 如果是没有库存的产品且自动发货 不用去商品中获取具体商品 产品中自带商品
                String productDetail = product.getProductDetail();
                String email = order.getEmail();
                emailCodeClient.sendEmailOrder(email, order, productDetail, 1);
            }


        }

        // 修改订单状态
        order.setOrderStatus(Constant.OrderStatus.FINISH.getStatusCode());
        this.baseMapper.updateById(order);
    }

    /**
     * 根据订单号查询订单
     *
     * @param orderSn
     * @return
     */
    @Override
    public OrderVo requestOrderByOrderSn(String orderSn) {

        Order order = this.baseMapper.selectById(orderSn);

        if (!ObjectUtils.isEmpty(order)) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo);
            if (order.getOrderStatus() == Constant.OrderStatus.FINISH.getStatusCode()) {

                // 判断订单产品是否是无库存类型的产品
                Product product = productService.requestProductById(order.getProductId());

                if (product.getType() == Constant.ProductType.HAS_STOCK.getStatusCode()) {
                    // 根据商品id们查询具体商品
                    List<Goods> goodsList = goodsService.requestGoodsListByIds(getGoodsIdList(order.getGoodsDetailIds()));

                    List<String> goodsDetailList = goodsList.stream().map(goods -> goods.getGoodsDetail()).collect(Collectors.toList());

                    orderVo.setGoodsDetailList(goodsDetailList);
                } else {
                    // 如果产品是没有库存类型的产品 那么直接返回
                    String productDetail = product.getProductDetail();

                    List<String> goodsDetailList = new ArrayList<>();

                    goodsDetailList.add(productDetail);

                    orderVo.setGoodsDetailList(goodsDetailList);
                }


                return orderVo;

            }
            return orderVo;
        }
        return null;

    }


    /**
     * 统计指定状态的订单个数
     *
     * @param statusCode
     * @return
     */
    @Override
    public Integer statisticsOrderByStatus(Integer statusCode) {

        return this.baseMapper.selectCount(new LambdaQueryWrapper<Order>().eq(Order::getOrderStatus, statusCode));

    }

    /**
     * 构造订单 易支付支付
     *
     * @param orderPayVo
     * @param ipAddress
     * @param device
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public EPayVo constructOrderPayDataEPay(OrderPayVo orderPayVo, String ipAddress, String device) {

        String orderSn = IdWorker.get32UUID();

        // 填充订单信息
        Order order = new Order();
        order.setOrderSn(orderSn);
        order.setOrderStatus(Constant.OrderStatus.LOCK.getStatusCode());
        order.setOrderTotalPrice(orderPayVo.getOrderTotalPrice());
        order.setGoodsPrice(orderPayVo.getGoodsPrice());
        order.setEmail(orderPayVo.getEmail());
        order.setGoodsNum(orderPayVo.getGoodsNum());
        order.setProductName(orderPayVo.getProductName());
        order.setProductId(orderPayVo.getProductId());

        // 判断是否是无库存产品 无库存产品的具体实现不在商品逻辑中 也不需要扣减库存 修改状态
        if (orderPayVo.getType() == Constant.ProductType.HAS_STOCK.getStatusCode()) {
            // 修改商品状态
            List<Integer> lockGoodsIdList = goodsService.lockGoodsByProductId(orderPayVo.getProductId(), orderPayVo.getGoodsNum());

            if (lockGoodsIdList.size() == orderPayVo.getGoodsNum()) {
                // 产品锁定库存修改
                productService.localProductStock(orderPayVo.getProductId(), orderPayVo.getGoodsNum());

            }

            StringBuffer goodsDetailIds = new StringBuffer();
            for (Integer goodsId : lockGoodsIdList) {
                goodsDetailIds.append(goodsId);
                if (lockGoodsIdList.get(orderPayVo.getGoodsNum() - 1) != goodsId) {
                    goodsDetailIds.append(":");
                }
            }

            order.setGoodsDetailIds(goodsDetailIds.toString());
        }

        // TODO 发送到延时队列
        // 75.127.13.112
        orderAutoCancelPublisher.sendOrderCancelTimeDelayMessage(order);

        // 保存订单
        this.baseMapper.insert(order);


        // 填充支付对象
        EPayVo ePayVo = new EPayVo();
        ePayVo.setName(orderPayVo.getProductName());
        ePayVo.setType(orderPayVo.getPayType());
        ePayVo.setOut_trade_no(orderSn);
        ePayVo.setReturnUrl(Constant.RETURN_URL);
        ePayVo.setNotifyUrl(Constant.NOTIFY_URL);
        ePayVo.setP_id(Constant.EASY_PID);
        ePayVo.setSign_type(Constant.EASY_SIGN_TYPE);
        ePayVo.setClientip(ipAddress);
        ePayVo.setDevice(device);
        ePayVo.setParam(orderSn);
        ePayVo.setMoney(String.valueOf(orderPayVo.getOrderTotalPrice()));

        ePayVo.setSign(MessageDigestHexUtil.doDigest(
                "clientip=" + ePayVo.getClientip() +
                        "&device=" + device +
                        "&money=" + ePayVo.getMoney() +
                        "&name=" + ePayVo.getName() +
                        "&notify_url=" + ePayVo.getNotifyUrl() +
                        "&out_trade_no=" + ePayVo.getOut_trade_no() +
                        "&param=" + ePayVo.getParam() +
                        "&pid=" + ePayVo.getP_id() +
                        "&return_url=" + ePayVo.getReturnUrl() +
                        "&type=" + ePayVo.getType() + Constant.EASY_KEY
        ).getStrCode());
        return ePayVo;

    }

}
