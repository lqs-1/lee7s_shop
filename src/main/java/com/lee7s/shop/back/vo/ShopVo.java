package com.lee7s.shop.back.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/26 16:20
 * @do 商城商品展示vo
 */

@Data
public class ShopVo {

    private Integer productCategoryId;

    private String productCategoryName;

    private String productCategoryLogo;

    private List<ShopProductItemVo> shopProductItemVoList;

}
