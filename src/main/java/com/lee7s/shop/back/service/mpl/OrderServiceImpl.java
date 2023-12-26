package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.entity.Order;
import com.lee7s.shop.back.mapper.OrderMapper;
import com.lee7s.shop.back.mq.publisher.OrderAutoCancelPublisher;
import com.lee7s.shop.back.service.GoodsService;
import com.lee7s.shop.back.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.utils.MessageDigestHexUtil;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.vo.OrderPayVo;
import com.lee7s.shop.back.vo.PayVo;
import com.lee7s.shop.back.vo.VPayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;


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


    /**
     * 构造订单 alipay
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

        // 修改商品状态
        List<Integer> lockGoodsIdList = goodsService.lockGoodsByProductId(orderPayVo.getProductId(), orderPayVo.getGoodsNum());

        if (lockGoodsIdList.size() == orderPayVo.getGoodsNum()){
            // 产品锁定库存修改
            productService.localProductStock(orderPayVo.getProductId(), orderPayVo.getGoodsNum());

        }

        StringBuffer goodsDetailIds = new StringBuffer();
        for (Integer goodsId : lockGoodsIdList) {
            goodsDetailIds.append(goodsId);
            if (lockGoodsIdList.get(orderPayVo.getGoodsNum() - 1) != goodsId){
                goodsDetailIds.append(":");
            }
        }

        order.setGoodsDetailIds(goodsDetailIds.toString());

        // TODO 发送到延时队列
        // 75.127.13.112
        orderAutoCancelPublisher.sendOrderCancelTimeDelayMessage(order);

        // 保存订单
        this.baseMapper.insert(order);



        // 填充支付对象
        VPayVo vPayVo = new VPayVo();
        vPayVo.setOut_trade_no(orderSn);
        vPayVo.setPayMethod(orderPayVo.getPayMethod());
        vPayVo.setTotal_amount(String.valueOf(orderPayVo .getOrderTotalPrice()));
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
     * @param param
     * @return
     */
    @Override
    public PageUtils requestOrderPageList(Map<String, Object> param) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<Order>();
        if (!ObjectUtils.isEmpty(param.get("status"))){
            queryWrapper.eq(Order::getOrderStatus, Integer.parseInt((String) param.get("status")));
        }

        IPage<Order> page = this.page(new QueryPage<Order>().getPage(param, true), queryWrapper);

        return new  PageUtils(page);

    }

    /**
     * 自动取消订单 超时未支付
     * @param order
     */
    @Override
    public void orderAutoCancel(Order order) {

    }


}
