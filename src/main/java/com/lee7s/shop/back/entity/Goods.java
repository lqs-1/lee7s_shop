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
@TableName("goods")
@ApiModel(value = "Goods对象", description = "")
public class Goods {

    @ApiModelProperty("商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品所属分类id")
    @TableField("catalog_id")
    private Integer catalogId;

    @ApiModelProperty("商品名字")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty("商品图片 地址")
    @TableField("goods_img")
    private String goodsImg;

    @ApiModelProperty("商品单价")
    @TableField("price")
    private Integer price;

    @ApiModelProperty("商品创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("商品更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("商品是否删除 0 未删除 1 删除")
    @TableField("is_delete")
    private Integer isDelete;


}
