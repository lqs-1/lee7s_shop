package com.lee7s.shop.back.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
@Getter
@Setter
@TableName("order")
@ApiModel(value = "Order对象", description = "")
public class Order {

    @ApiModelProperty("订单号")
    @TableId("order_sn")
    private String orderSn;

    @ApiModelProperty("购买的商品id")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty("购买的商品名字")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty("购买的商品单价")
    @TableField("goods_price")
    private Integer goodsPrice;

    @ApiModelProperty("购买的商品数量")
    @TableField("goods_num")
    private Integer goodsNum;

    @ApiModelProperty("订单总价")
    @TableField("order_total_price")
    private Integer orderTotalPrice;

    @ApiModelProperty("订单状态 0 待审核 1 已完成 2 已取消")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty("客户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("订单创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("订单更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


    @ApiModelProperty("具体商品 因为是虚拟的商品 用 : 隔开")
    @TableField("goods_detail_ids")
    private String goodsDetailIds;


}
