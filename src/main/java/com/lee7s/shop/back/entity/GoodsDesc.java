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
@TableName("goods_desc")
@ApiModel(value = "GoodsDesc对象", description = "")
public class GoodsDesc {

    @ApiModelProperty("商品详情id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty("商品注意事项 用:隔开")
    @TableField("goods_desc")
    private String goodsDesc;

    @ApiModelProperty("商品警告 用:隔开")
    @TableField("goods_warn")
    private String goodsWarn;

    @ApiModelProperty("商品描述创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("商品描述更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
