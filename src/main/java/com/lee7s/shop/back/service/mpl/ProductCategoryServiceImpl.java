package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.cloud.storage.abs.auto.UploadPlusProperties;
import com.lee7s.shop.back.cloud.storage.abs.upload.FileUploadPlus;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.ProductCategory;
import com.lee7s.shop.back.mapper.ProductCategoryMapper;
import com.lee7s.shop.back.service.ProductCategoryService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ProductCategoryIdAndNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee7s
 * @since 2023-12-24 10:26:13
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private UploadPlusProperties uploadPlusProperties;

    @Autowired
    private FileUploadPlus fileUploadPlus;

    /**
     * 获取产品分类分页数据
     * @param param
     * @return
     */
    @Override
    public PageUtils requestProductCategoryPageList(Map<String, Object> param) {
        IPage<ProductCategory> page = this.page(new QueryPage<ProductCategory>().getPage(param, true),
                new LambdaQueryWrapper<ProductCategory>().like(ProductCategory::getProductCategoryName,
                        (String) param.get("productCategoryName")));
        page.getRecords().stream().forEach(item -> {
            item.setProductCategoryLogo(uploadPlusProperties.getBaseUrl() + item.getProductCategoryLogo());
        });

        return new  PageUtils(page);
    }


    /**
     * 添加产品分类
     * @param productCategory
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R appendProductCategory(ProductCategory productCategory) {

        // 查询分类是否存在
        int same = this.baseMapper.selectList(new LambdaQueryWrapper<ProductCategory>().eq(ProductCategory::getProductCategoryName, productCategory.getProductCategoryName())).size();
        if (same >= 1) {
            return R.error(REnum.PRODUCT_CATEGORY_EXIST.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_EXIST.getStatusMsg());
        }

        // 正常则添加
        this.baseMapper.insert(productCategory);

        return R.ok(REnum.PRODUCT_CATEGORY_APPEND_SUCCESS.getStatusCode(),
                REnum.PRODUCT_CATEGORY_APPEND_SUCCESS.getStatusMsg());
    }


    /**
     * 修改产品分类
     * @param productCategory
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R alterProductCategory(ProductCategory productCategory) {
        // 查询分类是否存在
        int same = this.baseMapper.selectList(new LambdaQueryWrapper<ProductCategory>().eq(ProductCategory::getProductCategoryName, productCategory.getProductCategoryName())).size();
        if (same >= 2){
            return R.error(REnum.PRODUCT_CATEGORY_EXIST.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_EXIST.getStatusMsg());
        }


        if (productCategory.getProductCategoryLogo().contains(uploadPlusProperties.getBaseUrl())){
            productCategory.setProductCategoryLogo(fileUploadPlus.build().extractFileFullPath((String) productCategory.getProductCategoryLogo(), true));
        }

        this.baseMapper.updateById(productCategory);

        return R.ok(REnum.PRODUCT_CATEGORY_ALTER_SUCCESS.getStatusCode(),
                REnum.PRODUCT_CATEGORY_ALTER_SUCCESS.getStatusMsg());
    }


    /**
     * 删除产品分类
     * @param id
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void removeProductCategory(Integer id) {
        this.baseMapper.deleteById(id);
    }


    /**
     * 修改产品分类上架状态
     * @param productCategory
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R alterProductCategoryStatus(ProductCategory productCategory) {
        this.baseMapper.updateById(productCategory);
        return R.ok(REnum.PRODUCT_CATEGORY_STATUS_ALTER_SUCCESS.getStatusCode(),
                REnum.PRODUCT_CATEGORY_STATUS_ALTER_SUCCESS.getStatusMsg());
    }

    /**
     * 获取可用产品分类id和名字
     * @return
     */
    @Override
    public List<ProductCategoryIdAndNameVo> requestProductCategoryIdAndNameList() {

        List<ProductCategory> productCategoryList = this.baseMapper.selectList(new LambdaQueryWrapper<ProductCategory>().eq(ProductCategory::getStatus, Constant.ProductCategoryStatus.ON.getStatusCode()));

        List<ProductCategoryIdAndNameVo> productCategoryIdAndNameVos = productCategoryList.stream().map(productCategory -> {
            ProductCategoryIdAndNameVo productCategoryIdAndNameVo = new ProductCategoryIdAndNameVo();
            productCategoryIdAndNameVo.setProductCategoryId(productCategory.getProductCategoryId());
            productCategoryIdAndNameVo.setProductCategoryName(productCategory.getProductCategoryName());

            return productCategoryIdAndNameVo;
        }).collect(Collectors.toList());

        return productCategoryIdAndNameVos;
    }

    /**
     * 根据产品分类id查询产品分类名称
     * @param productCategoryId
     * @return
     */
    @Override
    public String requestProductCategoryNameById(Integer productCategoryId) {

        ProductCategory productCategory = this.baseMapper.selectById(productCategoryId);

        return productCategory.getProductCategoryName();

    }
}
