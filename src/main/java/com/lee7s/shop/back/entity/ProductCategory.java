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
@TableName("product_category")
@ApiModel(value = "ProductCategory对象", description = "")
public class ProductCategory {

    @ApiModelProperty("产品分类id")
    @TableId(value = "product_category_id", type = IdType.AUTO)
    private Integer productCategoryId;

    @ApiModelProperty("产品分类名称")
    @TableField("product_category_name")
    private String productCategoryName;

    @ApiModelProperty("产品分类logo")
    @TableField("product_category_logo")
    private String productCategoryLogo;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("产品分类是否删除 0 未删除 1 已删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("上架状态 0 上架 1 下架")
    @TableField("status")
    private Integer status;

}
