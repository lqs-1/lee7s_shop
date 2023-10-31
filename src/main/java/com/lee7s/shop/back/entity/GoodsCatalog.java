package com.lee7s.shop.back.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("goods_catalog")
@ApiModel(value = "GoodsCatalog对象", description = "")
public class GoodsCatalog {

    @ApiModelProperty("商品分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品分类id")
    @TableField("catalog_name")
    private String catalogName;

    @ApiModelProperty("分类描述")
    @TableField("catalog_desc")
    private String catalogDesc;

    @ApiModelProperty("商品分类logo 网址")
    @TableField("catalog_logo")
    private String catalogLogo;

    @ApiModelProperty("商品分类创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("商品分类更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("商品分类是否删除 0 未删除 1 删除")
    @TableField("is_delete")
    private Integer isDelete;


}
