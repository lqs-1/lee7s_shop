package com.lee7s.shop.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.entity.ProductCategory;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;

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
}
