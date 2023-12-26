package com.lee7s.shop.back.service;

import com.lee7s.shop.back.vo.ShopVo;

import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/26 17:19
 * @do 商城页面商品服务层接口
 */
public interface ShopService {
    List<ShopVo> requestShopGoodsInformation();
}
