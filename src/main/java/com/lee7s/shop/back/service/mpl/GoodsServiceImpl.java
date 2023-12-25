package com.lee7s.shop.back.service.mpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.Goods;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.mapper.GoodsMapper;
import com.lee7s.shop.back.service.GoodsService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.Pagination.QueryPage;
import com.lee7s.shop.back.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取商品分页数据
     * @param param
     * @return
     */
    @Override
    public PageUtils requestGoodsPageList(Map<String, Object> param) {


        IPage<Goods> page = this.page(new QueryPage<Goods>().getPage(param, true),
                new LambdaQueryWrapper<Goods>().like(Goods::getGoodsDetail,
                        (String) param.get("goodsDetail")).eq(Goods::getIsDelete, 0));

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
        int same = this.baseMapper.selectList(new LambdaQueryWrapper<Goods>().eq(Goods::getGoodsDetail, goods.getGoodsDetail())).size();
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
        List<Goods> dbGoodsList= this.baseMapper.selectList(new LambdaQueryWrapper<Goods>().eq(Goods::getGoodsDetail, goods.getGoodsDetail()));
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
}




