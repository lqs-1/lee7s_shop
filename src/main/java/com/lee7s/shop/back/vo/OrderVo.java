package com.lee7s.shop.back.vo;

import com.lee7s.shop.back.entity.Order;
import lombok.Data;

import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/27 17:14
 * @do 订单视图 用户查询订单的时候返回
 */
@Data
public class OrderVo extends Order {


    private List<String> goodsDetailList;



}
