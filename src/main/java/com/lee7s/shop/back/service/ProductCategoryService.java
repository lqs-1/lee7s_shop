package com.lee7s.shop.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.entity.ProductCategory;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ProductCategoryIdAndNameVo;

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
public interface ProductCategoryService extends IService<ProductCategory> {

    PageUtils requestProductCategoryPageList(Map<String, Object> param);

    R appendProductCategory(ProductCategory productCategory);

    R alterProductCategory(ProductCategory productCategory);

    void removeProductCategory(Integer id);

    R alterProductCategoryStatus(ProductCategory productCategory);

    List<ProductCategoryIdAndNameVo> requestProductCategoryIdAndNameList();

    String requestProductCategoryNameById(Integer productCategoryId);
}
