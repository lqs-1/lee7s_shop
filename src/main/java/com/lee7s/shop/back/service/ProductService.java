package com.lee7s.shop.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ProductIdAndNameVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee7s
 * @since 2023-12-24 10:26:13
 */
public interface ProductService extends IService<Product> {

    PageUtils requestProductPageList(Map<String, Object> param);

    R appendProduct(Product product);

    R alterProduct(Product product);

    void removeProduct(Integer id);

    R alterProductStatus(Product product);

    List<ProductIdAndNameVo> requestProductIdAndNameListByCategoryId(Integer productCategoryId);

    Product requestProductNameByProductId(Integer productId);

    void updateStock(Integer productId, Integer stockNum);

    List<Product> requestAvailableProductList(Integer productCategoryId);

    void localProductStock(Integer productId, Integer num);

    void recoverProductLockStock(Integer productId, Integer num);

    void deductionStock(Integer productId, Integer goodsNum);

    Boolean hasLockStockProductByProductCategoryId(Integer productCategoryId);

    Product requestProductById(Integer productId);
}
