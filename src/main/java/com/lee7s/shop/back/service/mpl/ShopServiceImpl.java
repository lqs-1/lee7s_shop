package com.lee7s.shop.back.service.mpl;

import com.lee7s.shop.back.cloud.storage.abs.auto.UploadPlusProperties;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.entity.ProductCategory;
import com.lee7s.shop.back.service.ProductCategoryService;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.service.ShopService;
import com.lee7s.shop.back.vo.ShopProductItemVo;
import com.lee7s.shop.back.vo.ShopVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/26 17:20
 * @do 商城页面商品服务层实现
 */
@Service
public class ShopServiceImpl implements ShopService {


    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UploadPlusProperties uploadPlusProperties;



    /**
     * 获取商城页面商品信息
     * @return
     */
    @Override
    public List<ShopVo> requestShopGoodsInformation() {

        List<ShopVo> shopGoodsInformationList = new ArrayList<>();

        // 查询可用产品分类列表
        List<ProductCategory> productCategoryList = productCategoryService.requestAvailableProductCategoryList();

        productCategoryList.stream().forEach(productCategory -> {
            ShopVo shopVo = new ShopVo();
            shopVo.setProductCategoryId(productCategory.getProductCategoryId());
            shopVo.setProductCategoryName(productCategory.getProductCategoryName());
            shopVo.setProductCategoryLogo(uploadPlusProperties.getBaseUrl() + productCategory.getProductCategoryLogo());

            // 查询产品分类下可用的产品
            List<ShopProductItemVo> shopProductItemList = new ArrayList<>();
            List<Product> productList = productService.requestAvailableProductList(productCategory.getProductCategoryId());

            productList.stream().forEach(product -> {
                ShopProductItemVo shopProductItemVo = new ShopProductItemVo();
                BeanUtils.copyProperties(product, shopProductItemVo);
                shopProductItemVo.setProductLogo(uploadPlusProperties.getBaseUrl() + shopProductItemVo.getProductLogo());
                shopProductItemVo.setProductStock(product.getProductStock() - product.getProductLockStock());
                shopProductItemList.add(shopProductItemVo);
            });

            shopVo.setShopProductItemVoList(shopProductItemList);

            shopGoodsInformationList.add(shopVo);

        });

        return shopGoodsInformationList;
    }
}
