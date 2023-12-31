package com.lee7s.shop.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author somg
 * @date 2023/5/1 20:13
 * @do 系统字典
 */

@Data
@TableName("sys_dict")
@ApiModel(value = "SysDict对象", description = "")
public class SysDict {

    @ApiModelProperty("字典id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("字典key")
    @TableField(value = "dict_code")
    private String  dictCode;

    @ApiModelProperty("子字典的父节点id")
    @TableField(value = "parent_id")
    private Long parentId;

    @ApiModelProperty("字典描述")
    @TableField(value = "dict_desc")
    private String dictDesc;

    @ApiModelProperty("字典值")
    @TableField(value = "dict_value")
    private String dictValue;

}