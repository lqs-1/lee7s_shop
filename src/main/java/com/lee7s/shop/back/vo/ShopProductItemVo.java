package com.lee7s.shop.back.vo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lee7s
 * @date 2023/12/26 17:11
 * @do 商城页面商品数据的产品项
 */
@Data
public class ShopProductItemVo {
    private Integer productId;

    private Integer productCategoryId;

    private String productName;

    private Integer productPrice;

    private String productLogo;

    private Integer productMethod;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDelete;

    private Integer status;

    private String productDesc;

    private String productWarn;

    private Integer productStock;

    private Integer productLockStock;
}
