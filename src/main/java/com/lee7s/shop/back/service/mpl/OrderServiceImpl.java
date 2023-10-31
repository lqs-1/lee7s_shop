package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.entity.GoodsDesc;
import com.lee7s.shop.back.entity.GoodsDetail;
import com.lee7s.shop.back.entity.GoodsStock;
import com.lee7s.shop.back.entity.Order;
import com.lee7s.shop.back.mapper.OrderMapper;
import com.lee7s.shop.back.service.GoodsDetailService;
import com.lee7s.shop.back.service.GoodsStockService;
import com.lee7s.shop.back.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.vo.GoodsPayVo;
import com.lee7s.shop.back.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private GoodsStockService goodsStockService;

    @Autowired
    private GoodsDetailService goodsDetailService;


    /**
     * 构造订单数据 锁定库存等
     *
     * @param goodsPayVo
     * @return
     */
    @Override
    public String constructOrderPayData(GoodsPayVo goodsPayVo) {

        Order order = new Order();

        String order_sn = IdWorker.get32UUID();
       /* String subject = goodsPayVo.getGoodsName();
        String total_amount = String.valueOf(goodsPayVo.getTotal());
        String orderDesc = goodsPayVo.getGoodsName();*/

        // 填充订单
        order.setOrderSn(order_sn);
        order.setOrderStatus(Constant.OrderStatus.MODERATED.getStatusCode());
        order.setEmail(goodsPayVo.getEmail());
        order.setOrderTotalPrice(goodsPayVo.getTotal());
        order.setGoodsId(goodsPayVo.getId());
        order.setGoodsName(goodsPayVo.getGoodsName());
        order.setGoodsPrice(goodsPayVo.getPrice());
        order.setGoodsNum(goodsPayVo.getNum());

        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());


        // 库存
        GoodsStock goodsStock = goodsStockService.getBaseMapper().selectOne(new LambdaQueryWrapper<GoodsStock>().eq(GoodsStock::getGoodsId, goodsPayVo.getId()));

        if (goodsStock.getStock() < goodsPayVo.getNum()) return null;

        goodsStock.setStock(goodsStock.getStock() - goodsPayVo.getNum());
        goodsStock.setLockStock(goodsStock.getLockStock() + goodsPayVo.getNum());

        goodsStockService.updateById(goodsStock);

        // 修改具体商品状态
        Stream<GoodsDetail> goodsDetailStream = goodsDetailService.getBaseMapper().selectList(new LambdaQueryWrapper<GoodsDetail>().eq(GoodsDetail::getGoodsId, goodsPayVo.getId())).stream().limit(goodsPayVo.getNum());

        goodsDetailStream.forEach(goodsDetail -> {
            goodsDetail.setStatus(Constant.GoodsStatus.SOLD.getStatusCode());
            goodsDetailService.updateById(goodsDetail);
        });

        List<Integer> goodsDetailIdList = goodsDetailStream.map(GoodsDetail::getId).collect(Collectors.toList());
        StringBuilder goodsDetailIds = new StringBuilder();

        for (int i = 0; i < goodsDetailIdList.size(); i++){
            String goodsDetailId = String.valueOf(goodsDetailIdList.get(i));
            goodsDetailIds.append(goodsDetailId);
            if (i < goodsDetailIdList.size() - 1){
                goodsDetailIds.append(":");
            }
        }

        order.setGoodsDetailIds(goodsDetailIds.toString());

        // 保存订单
        this.save(order);

        /*PayVo payVo = new PayVo();
        payVo.setOrderDesc(orderDesc);
        payVo.setSubject(subject);
        payVo.setOut_trade_no(order_sn);
        payVo.setTotal_amount(total_amount);

        payVo.setTimeout_express(Constant.ORDER_PAY_TIMEOUT_EXPIRE);*/

        return goodsPayVo.getMoneyToken();


    }
}
