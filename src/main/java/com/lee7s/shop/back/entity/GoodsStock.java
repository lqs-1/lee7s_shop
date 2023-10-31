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
@TableName("goods_stock")
@ApiModel(value = "GoodsStock对象", description = "")
public class GoodsStock {

    @ApiModelProperty("商品库存id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty("商品库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty("商品锁定库存")
    @TableField("lock_stock")
    private Integer lockStock;

    @ApiModelProperty("商品库存创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("商品库存更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
