package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.cloud.storage.abs.auto.UploadPlusProperties;
import com.lee7s.shop.back.cloud.storage.abs.upload.FileUploadPlus;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.mapper.ProductMapper;
import com.lee7s.shop.back.service.ProductCategoryService;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private UploadPlusProperties uploadPlusProperties;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private FileUploadPlus fileUploadPlus;

    /**
     * 获取产品分页数据
     * @param param
     * @return
     */
    @Override
    public PageUtils requestProductPageList(Map<String, Object> param) {

        IPage<Product> page = this.page(new QueryPage<Product>().getPage(param, true),
                new LambdaQueryWrapper<Product>().like(Product::getProductName,
                        (String) param.get("productName")));



        page.getRecords().stream().forEach(item -> {
            item.setProductLogo(uploadPlusProperties.getBaseUrl() + item.getProductLogo());
            item.setProductCategoryName(productCategoryService.requestProductCategoryNameById(item.getProductCategoryId()));
        });

        return new  PageUtils(page);
    }


    /**
     * 添加产品
     * @param product
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R appendProduct(Product product) {
        // 查询是否存在
        int same = this.baseMapper.selectList(new LambdaQueryWrapper<Product>().eq(Product::getProductName, product.getProductName())).size();
        if (same >= 1) {
            return R.error(REnum.PRODUCT_EXIST.getStatusCode(),
                    REnum.PRODUCT_EXIST.getStatusMsg());
        }

        // 正常则添加
        this.baseMapper.insert(product);

        return R.ok(REnum.PRODUCT_APPEND_SUCCESS.getStatusCode(),
                REnum.PRODUCT_APPEND_SUCCESS.getStatusMsg());
    }


    /**
     * 修改产品
     * @param product
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R alterProduct(Product product) {
        // 查询是否存在
        int same = this.baseMapper.selectList(new LambdaQueryWrapper<Product>().eq(Product::getProductName, product.getProductName())).size();
        if (same >= 2) {
            return R.error(REnum.PRODUCT_EXIST.getStatusCode(),
                    REnum.PRODUCT_EXIST.getStatusMsg());
        }

        if (product.getProductLogo().contains(uploadPlusProperties.getBaseUrl())){
            product.setProductLogo(fileUploadPlus.build().extractFileFullPath((String) product.getProductLogo(), true));
        }

        // 正常则添加
        this.baseMapper.updateById(product);

        return R.ok(REnum.PRODUCT_ALTER_SUCCESS.getStatusCode(),
                REnum.PRODUCT_ALTER_SUCCESS.getStatusMsg());
    }


    /**
     * 删除产品
     * @param id
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void removeProduct(Integer id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 修改产品上架状态
     * @param product
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)

    @Override
    public R alterProductStatus(Product product) {

        this.baseMapper.updateById(product);

        return R.ok(REnum.PRODUCT_STATUS_ALTER_SUCCESS.getStatusCode(),
                REnum.PRODUCT_STATUS_ALTER_SUCCESS.getStatusMsg());

    }
}
