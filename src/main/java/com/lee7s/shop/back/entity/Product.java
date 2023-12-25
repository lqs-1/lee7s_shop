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
@TableName("product")
@ApiModel(value = "Product对象", description = "")
public class Product {

    @ApiModelProperty("产品id")
    @TableId("product_id")
    private Integer productId;

    @ApiModelProperty("产品分类id")
    @TableField("product_category_id")
    private Integer productCategoryId;

    @ApiModelProperty("产品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("产品价格")
    @TableField("product_price")
    private Integer productPrice;

    @ApiModelProperty("产品logo")
    @TableField("product_logo")
    private String productLogo;

    @ApiModelProperty("发货方式 0 自动 1 手动")
    @TableField("product_method")
    private Integer productMethod;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("产品是否删除 0 未删除 1 已删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("上架状态 0 上架 1 下架")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("产品注意事项 用:隔开")
    @TableField("product_desc")
    private String productDesc;

    @ApiModelProperty("产品警告 用:隔开")
    @TableField("product_warn")
    private String productWarn;

    @ApiModelProperty("产品库存")
    @TableField("product_stock")
    private Integer productStock;

    @ApiModelProperty("产品锁定库存")
    @TableField("product_lock_stock")
    private Integer productLockStock;

    @TableField(exist = false)
    private String productCategoryName;


}
