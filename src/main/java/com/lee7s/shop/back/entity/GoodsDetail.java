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
 * @since 2023-10-07 04:04:45
 */
@Getter
@Setter
@TableName("goods_detail")
@ApiModel(value = "GoodsDetail对象", description = "")
public class GoodsDetail {

    @ApiModelProperty("具体商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("所属商品id")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty("商品详情")
    @TableField("detail")
    private String detail;

    @ApiModelProperty("删除 0 未删除 1 已删除")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("商品状态 0 可用 1 已售")
    @TableField("status")
    private Integer status;


}
