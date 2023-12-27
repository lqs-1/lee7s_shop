package com.lee7s.shop.back.controller.shop;

import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.service.ShopService;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ShopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/26 16:19
 * @do 商城商品数据控制器
 */

@RestController
@RequestMapping("/shop/goods")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 获取商城页面商品信息
     * @return
     */
    @GetMapping("requestShopGoodsInformation")
    public R requestShopGoodsInformation(){

        try {

            List<ShopVo> shopGoodsInformationList = shopService.requestShopGoodsInformation();

            return R.ok(REnum.SHOP_MALL_PAGE_GOODS_REQUEST_SUCCESS.getStatusCode(),
                    REnum.SHOP_MALL_PAGE_GOODS_REQUEST_SUCCESS.getStatusMsg())
                    .put("shopGoodsInformationList", shopGoodsInformationList);

        }catch (Exception e){
            e.printStackTrace();

            return R.error(REnum.SHOP_MALL_PAGE_GOODS_REQUEST_FAIL.getStatusCode(),
                    REnum.SHOP_MALL_PAGE_GOODS_REQUEST_FAIL.getStatusMsg());
        }

    }

}
