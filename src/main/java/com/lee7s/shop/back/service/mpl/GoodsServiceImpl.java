package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.Goods;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.mapper.GoodsMapper;
import com.lee7s.shop.back.service.GoodsService;
import com.lee7s.shop.back.service.ProductCategoryService;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.utils.R;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取商品分页数据
     * @param param
     * @return
     */
    @Override
    public PageUtils requestGoodsPageList(Map<String, Object> param) {

        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<Goods>().like(Goods::getGoodsDetail,
                (String) param.get("goodsDetail"));

        if (!ObjectUtils.isEmpty(param.get("productId"))){
            queryWrapper.eq(Goods::getProductId, Integer.parseInt((String) param.get("productId")));
        }

        if (!ObjectUtils.isEmpty(param.get("status"))){
            queryWrapper.eq(Goods::getStatus, Integer.parseInt((String) param.get("status")));
        }


        IPage<Goods> page = this.page(new QueryPage<Goods>().getPage(param, true), queryWrapper);

        page.getRecords().stream().forEach(goods -> {
            Product product = productService.requestProductNameByProductId(goods.getProductId());
            goods.setProductName(product.getProductName());
            goods.setProductCategoryId(product.getProductCategoryId());
        });

        return new  PageUtils(page);
    }


    /**
     * 添加商品
     * @param goods
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public R appendGoods(Goods goods) {

        // 查询是否存在
        int same = this.baseMapper.selectList(new LambdaQueryWrapper<Goods>().eq(Goods::getGoodsDetail, goods.getGoodsDetail()).eq(Goods::getProductId, goods.getProductId())).size();
        if (same >= 1) {
            return R.error(REnum.GOODS_EXIST.getStatusCode(),
                    REnum.GOODS_EXIST.getStatusMsg());
        }

        // 正常则添加
        this.baseMapper.insert(goods);

        return R.ok(REnum.GOODS_APPEND_SUCCESS.getStatusCode(),
                REnum.GOODS_APPEND_SUCCESS.getStatusMsg());

    }


    /**
     * 修改商品
     * @param goods
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R alterGoods(Goods goods) {

        // 查询是否存在
        List<Goods> dbGoodsList= this.baseMapper.selectList(new LambdaQueryWrapper<Goods>().eq(Goods::getGoodsDetail, goods.getGoodsDetail()).eq(Goods::getProductId, goods.getProductId()));
        if (dbGoodsList.size() >= 1){
            for (Goods goods1 : dbGoodsList) {
                if (goods1.getGoodsId() != goods.getGoodsId()) {
                    return R.error(REnum.GOODS_EXIST.getStatusCode(),
                            REnum.GOODS_EXIST.getStatusMsg());
                }
            }
        }

        // 正常则添加
        this.baseMapper.updateById(goods);

        return R.ok(REnum.GOODS_ALTER_SUCCESS.getStatusCode(),
                REnum.GOODS_ALTER_SUCCESS.getStatusMsg());
    }

    /**
     * 删除商品
     * @param id
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void removeGoods(Integer id) {
        this.baseMapper.deleteById(id);
    }

    /**
     *
     * 修改商品上架状态
     * @param goods
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public R alterGoodsStatus(Goods goods) {

        Goods fullGoods = this.baseMapper.selectById(goods.getGoodsId());

        /**
         * 判断
         */

        if (goods.getStatus() == Constant.GoodsStatus.ON.getStatusCode()){
            // 上架
            // 商品可用 允许上架
            if (fullGoods.getGoodsStatus() == Constant.GoodsGoodsStatus.ON.getStatusCode()){
                this.baseMapper.updateById(goods);
                // 修改库存
                productService.updateStock(fullGoods.getProductId(), 1);
                return R.ok(REnum.GOODS_STATUS_ALTER_SUCCESS.getStatusCode(),
                        REnum.GOODS_STATUS_ALTER_SUCCESS.getStatusMsg());
            }
            // 商品已售无法上架 TODO 会在订单完成的时候自动下架 且无法再上架
            // 商品锁定无法上架 TODO 在用户提交订单后会自动改为锁定状态 自动改成下架模式 自动更新锁定库存 如果订单超时 会自动修改为上架和可用模式以及恢复锁定库存 如果订单成功了 商品改为已售 扣减库存
        }else {
            // 下架
            // 商品可用 允许下架
            if (fullGoods.getGoodsStatus() == Constant.GoodsGoodsStatus.ON.getStatusCode()){
                this.baseMapper.updateById(goods);
                // 修改库存
                productService.updateStock(fullGoods.getProductId(), -1);
                return R.ok(REnum.GOODS_STATUS_ALTER_SUCCESS.getStatusCode(),
                        REnum.GOODS_STATUS_ALTER_SUCCESS.getStatusMsg());
            }
            // 商品已售 不存在下架 TODO
            // 商品锁定 不存在下架 TODO
        }

        return R.error(REnum.GOODS_STATUS_ALTER_FAIL.getStatusCode(),
                REnum.GOODS_STATUS_ALTER_FAIL.getStatusMsg());


    }

    /**
     * 锁定指定产品id的商品 锁定指定个数
     * @param productId
     * @param goodsNum
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public List<Integer> lockGoodsByProductId(Integer productId, Integer goodsNum) {

        List<Integer> lockGoodsIdList = this.baseMapper.selectList(new LambdaQueryWrapper<Goods>()
                        .eq(Goods::getProductId, productId)
                        .eq(Goods::getStatus, Constant.GoodsStatus.ON.getStatusCode())
                        .eq(Goods::getGoodsStatus, Constant.GoodsGoodsStatus.ON.getStatusCode()))
                .stream()
                .limit(goodsNum)
                .map(goods -> {
                    goods.setGoodsStatus(Constant.GoodsGoodsStatus.LOCK.getStatusCode());
                    goods.setStatus(Constant.GoodsStatus.OFF.getStatusCode());
                    this.baseMapper.updateById(goods);
                    return goods.getGoodsId();
                }).collect(Collectors.toList());
        return lockGoodsIdList;
    }


    /**
     * 恢复商品状态 根据商品id
     * @param goodsIdList
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void recoverGoodsStatusByIds(List<Integer> goodsIdList) {
        goodsIdList.forEach(goodsId -> {
            Goods goods = this.baseMapper.selectById(goodsId);
            goods.setStatus(Constant.GoodsStatus.ON.getStatusCode());
            goods.setGoodsStatus(Constant.GoodsGoodsStatus.ON.getStatusCode());
            this.baseMapper.updateById(goods);
        });
    }


    /**
     * 修改商品们状态 并返回商品们
     * @param goodsIdList
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public List<Goods> alterGoodsGoodsStatusToOFF(List<Integer> goodsIdList) {
        List<Goods> saleGoodsList = this.baseMapper.selectBatchIds(goodsIdList).stream().map(goods -> {
            goods.setGoodsStatus(Constant.GoodsGoodsStatus.OFF.getStatusCode());
            return goods;
        }).collect(Collectors.toList());

        this.updateBatchById(saleGoodsList);
        return saleGoodsList;
    }


    /**
     * 根据商品id们 查询商品列表
     * @param goodsIdList
     * @return
     */
    @Override
    public List<Goods> requestGoodsListByIds(List<Integer> goodsIdList) {

        return this.baseMapper.selectBatchIds(goodsIdList);
    }

}




