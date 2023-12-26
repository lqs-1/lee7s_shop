package com.lee7s.shop.back.controller;

import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.Goods;
import com.lee7s.shop.back.entity.ProductCategory;
import com.lee7s.shop.back.service.GoodsService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee7s
 * @since 2023-12-24 10:26:13
 */
@RestController
@RequestMapping("/back/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     * 获取商品分页数据
     * @param param
     * @return
     */
    @GetMapping("requestPageList")
    public R requestPageList(@RequestParam Map<String, Object> param){

        try{

            PageUtils goodsPageList = goodsService.requestGoodsPageList(param);

            return R.ok(REnum.GET_GOODS_PAGE_LIST_SUCCESS.getStatusCode(),
                            REnum.GET_GOODS_PAGE_LIST_SUCCESS.getStatusMsg())
                    .put("goodsPageList", goodsPageList);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GET_GOODS_PAGE_LIST_FAIL.getStatusCode(),
                    REnum.GET_GOODS_PAGE_LIST_FAIL.getStatusMsg());
        }
    }


    /**
     * 添加商品
     * @param goods
     * @return
     */
    @PostMapping("appendGoods")
    public R appendGoods(@RequestBody Goods goods){

        try{

            R result = goodsService.appendGoods(goods);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GOODS_APPEND_FAIL.getStatusCode(),
                    REnum.GOODS_APPEND_FAIL.getStatusMsg());
        }

    }

    /**
     * 修改商品
     * @param goods
     * @return
     */
    @PutMapping("alterGoods")
    public R alterProduct(@RequestBody Goods goods){
        try{

            R result = goodsService.alterGoods(goods);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GOODS_ALTER_FAIL.getStatusCode(),
                    REnum.GOODS_ALTER_FAIL.getStatusMsg());
        }
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping("removeGoods/{id}")
    public R removeGoods(@PathVariable Integer id){
        try{

            goodsService.removeGoods(id);

            return R.ok(REnum.GOODS_REMOVE_SUCCESS.getStatusCode(),
                    REnum.GOODS_REMOVE_SUCCESS.getStatusMsg());

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GOODS_REMOVE_FAIL.getStatusCode(),
                    REnum.GOODS_REMOVE_FAIL.getStatusMsg());
        }
    }


    /**
     * 修改商品上架状态
     * @param goods
     * @return
     */
    @PutMapping("alterGoodsStatus")
    public R alterGoodsStatus(@RequestBody Goods goods){
        try{

            R result = goodsService.alterGoodsStatus(goods);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GOODS_STATUS_ALTER_FAIL.getStatusCode(),
                    REnum.GOODS_STATUS_ALTER_FAIL.getStatusMsg());
        }
    }


}
