package com.lee7s.shop.back.vo;

import lombok.Data;

/**
 * @author somg
 * @date 2023/10/7 10:44
 * @do 前端收集的用户商品订单数据
 */
@Data
public class GoodsPayVo {

    private Integer id; // 商品id

    private String goodsName; // 商品名字

    private String email; // 客户邮箱

    private Integer num; // 商品数量

    private Integer price; // 商品单价

    private Integer total; // 商品总价

    private String payMethod; // 支付方式 1微信 2支付宝


}
