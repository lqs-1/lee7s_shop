package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.cloud.storage.abs.auto.UploadPlusProperties;
import com.lee7s.shop.back.cloud.storage.abs.upload.FileUploadPlus;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.mapper.ProductMapper;
import com.lee7s.shop.back.service.ProductCategoryService;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ProductIdAndNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<Product>().like(Product::getProductName, (String) param.get("productName"));
        if (!ObjectUtils.isEmpty(param.get("productCategoryId"))){
            queryWrapper.eq(Product::getProductCategoryId, Integer.parseInt((String) param.get("productCategoryId")));
        }

        IPage<Product> page = this.page(new QueryPage<Product>().getPage(param, true), queryWrapper);



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

        if (product.getStatus() == Constant.ProductStatus.OFF.getStatusCode()){
            if (this.baseMapper.selectById(product.getProductId()).getProductLockStock() >= 1){
                return R.error(REnum.PRODUCT_STATUS_ALTER_FAIL.getStatusCode(),
                        REnum.PRODUCT_STATUS_ALTER_FAIL.getStatusMsg());
            }
        }

        this.baseMapper.updateById(product);

        return R.ok(REnum.PRODUCT_STATUS_ALTER_SUCCESS.getStatusCode(),
                REnum.PRODUCT_STATUS_ALTER_SUCCESS.getStatusMsg());

    }

    /**
     * 根据产品分类id获取可用产品id和名字
     * @return
     */
    @Override
    public List<ProductIdAndNameVo> requestProductIdAndNameListByCategoryId(Integer productCategoryId) {
        List<Product> productList = this.baseMapper.selectList(new LambdaQueryWrapper<Product>().eq(Product::getStatus, Constant.ProductStatus.ON.getStatusCode()).eq(Product::getProductCategoryId, productCategoryId));

        List<ProductIdAndNameVo> productIdAndNameVos = productList.stream()
                .filter(product -> product.getType() == Constant.ProductType.HAS_STOCK.getStatusCode())
                .map(product -> {
            ProductIdAndNameVo productIdAndNameVo = new ProductIdAndNameVo();
            productIdAndNameVo.setProductId(product.getProductId());
            productIdAndNameVo.setProductName(product.getProductName());

            return productIdAndNameVo;
        }).collect(Collectors.toList());

        return productIdAndNameVos;
    }


    /**
     * 根据产品id查询产品名称和产品所属分类id
     * 也就是返回对象 根据产品id查询对应的产品对象
     * @param productId
     * @return
     */
    @Override
    public Product requestProductNameByProductId(Integer productId) {

        return this.baseMapper.selectById(productId);

    }

    /**
     * 更改商品状态以后 根据产品id更新产品库存
     * @param productId
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void updateStock(Integer productId, Integer stockNum) {
        Product product = this.baseMapper.selectById(productId);
        product.setProductStock(product.getProductStock() + stockNum);
        this.baseMapper.updateById(product);
    }

    /**
     * 根据产品分类id查询可用的产品列表
     * @param productCategoryId
     * @return
     */
    @Override
    public List<Product> requestAvailableProductList(Integer productCategoryId) {

        return this.baseMapper.selectList(new LambdaQueryWrapper<Product>().eq(Product::getStatus, Constant.ProductStatus.ON.getStatusCode()).eq(Product::getProductCategoryId, productCategoryId));

    }

    /**
     * 修改产品锁定库存
     *
     * @param productId
     * @param num
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void localProductStock(Integer productId, Integer num) {
        Product product = this.baseMapper.selectById(productId);
        product.setProductLockStock(product.getProductLockStock() + num);
        this.baseMapper.updateById(product);
    }


    /**
     * 根据产品解锁库存 解锁锁定库存
     * @param productId
     * @param num
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void recoverProductLockStock(Integer productId, Integer num) {
        Product product = this.baseMapper.selectById(productId);
        product.setProductLockStock(product.getProductLockStock() - num);
        this.baseMapper.updateById(product);
    }

    /**
     * 支付成功 扣减库存
     * @param productId
     * @param goodsNum
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deductionStock(Integer productId, Integer goodsNum) {

        Product product = this.baseMapper.selectById(productId);
        product.setProductLockStock(product.getProductLockStock() - goodsNum);
        product.setProductStock(product.getProductStock() - goodsNum);

        this.baseMapper.updateById(product);
    }

    /**
     * 查看产品分类下是否存在有锁定库存的产品
     * @param productCategoryId
     * @return
     */
    @Override
    public Boolean hasLockStockProductByProductCategoryId(Integer productCategoryId) {

        Boolean hasStock = false;

        List<Product> productList = this.baseMapper.selectList(new LambdaQueryWrapper<Product>().eq(Product::getProductCategoryId, productCategoryId));

        for (Product product : productList) {
            if (product.getProductLockStock() >= 1){
                hasStock = true;
                break;
            }
        }
        return hasStock;
    }


    /**
     * 根据id获取产品
     * @param productId
     * @return
     */
    @Override
    public Product requestProductById(Integer productId) {

        return this.baseMapper.selectById(productId);

    }
}
