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
@TableName("goods")
@ApiModel(value = "Goods对象", description = "")
public class Goods {

    @ApiModelProperty("具体商品的id")
      @TableId("goods_id")
    private Integer goodsId;

    @ApiModelProperty("所属产品id")
    @TableField("product_id")
    private Integer productId;

    @ApiModelProperty("具体商品的详情 因为是账号所以这里就是账号信息")
    @TableField("goods_detail")
    private String goodsDetail;

    @ApiModelProperty("创建时间")
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("商品是否删除 0 未删除 1 已删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("下架状态 0 未下架 1 已下架")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("商品状态 0 可用 1 已售 2 锁定")
    @TableField("goods_status")
    private Integer goodsStatus;

    // 所属产品
    @TableField(exist = false)
    private String productName;

    // 所属产品分类id
    @TableField(exist = false)
    private Integer productCategoryId;

}
