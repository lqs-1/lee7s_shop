package com.lee7s.shop.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.entity.Order;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.vo.*;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
public interface OrderService extends IService<Order> {

    PayVo constructOrderPayDataAliPay(OrderPayVo orderPayVo);

    VPayVo constructOrderPayDataVPay(OrderPayVo orderPayVo);

    PageUtils requestOrderPageList(Map<String, Object> param);

    void orderAutoCancel(Order order);

    void completeOrder(String orderSn);

    OrderVo requestOrderByOrderSn(String orderSn);

    Integer statisticsOrderByStatus(Integer statusCode);

    EPayVo constructOrderPayDataEPay(OrderPayVo orderPayVo, String ipAddress, String device);

    void constructFreeOrder(OrderPayVo orderPayVo);
}
