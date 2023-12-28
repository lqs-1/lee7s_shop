package com.lee7s.shop.back.vo;

import lombok.Data;

/**
 * @author somg
 * @date 2023/10/7 10:44
 * @do 前端收集的用户商品订单数据
 */
@Data
public class OrderPayVo {

    private Integer productId; // 产品id

    private String productName; // 产品名字

    private String email; // 客户邮箱

    private Integer goodsNum; // 产品数量

    private Integer goodsPrice; // 产品单价

    private Integer orderTotalPrice; // 订单总额

    private String payMethod; // 支付方式 1微信 2支付宝

    private Integer type; // 产品类型 0 有库存的 1 无库存的


}
