package com.lee7s.shop.back.service;

import com.lee7s.shop.back.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.vo.GoodsPayVo;
import com.lee7s.shop.back.vo.PayVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
public interface OrderService extends IService<Order> {

    String constructOrderPayData(GoodsPayVo goodsPayVo);
}
