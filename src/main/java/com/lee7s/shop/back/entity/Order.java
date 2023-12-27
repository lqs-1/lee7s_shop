package com.lee7s.shop.back.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee7s
 * @since 2023-12-24 10:26:13
 */
@Getter
@Setter
@TableName("`order`")
@ApiModel(value = "Order对象", description = "")
public class Order {

    @ApiModelProperty("订单编号")
      @TableId(value = "order_sn", type = IdType.INPUT)
    private String orderSn;

    @ApiModelProperty("购买产品的id")
    @TableField("product_id")
    private Integer productId;

    @ApiModelProperty("购买产品的名字")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("商品单价")
    @TableField("goods_price")
    private Integer goodsPrice;

    @ApiModelProperty("订单总价")
    @TableField("order_total_price")
    private Integer orderTotalPrice;

    @ApiModelProperty("客户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("具体商品的id 可多个 用于解库存 : 分割")
    @TableField("goods_detail_ids")
    private String goodsDetailIds;

    @ApiModelProperty("订单状态 0 库存已经锁定 1 已完成 2 已取消")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty("创建时间")
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("商品数量")
    @TableField("goods_num")
    private Integer goodsNum;


}
