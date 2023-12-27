package com.lee7s.shop.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.entity.Goods;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;

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
public interface GoodsService extends IService<Goods> {

    PageUtils requestGoodsPageList(Map<String, Object> param);

    R appendGoods(Goods goods);

    R alterGoods(Goods goods);

    void removeGoods(Integer id);

    R alterGoodsStatus(Goods goods);

    List<Integer> lockGoodsByProductId(Integer productId, Integer goodsNum);

    void recoverGoodsStatusByIds(List<Integer> goodsIdList);

    List<Goods> alterGoodsGoodsStatusToOFF(List<Integer> goodsIdList);

    List<Goods> requestGoodsListByIds(List<Integer> goodsIdList);
}
