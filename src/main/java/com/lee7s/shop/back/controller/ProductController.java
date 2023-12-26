package com.lee7s.shop.back.controller;

import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.Product;
import com.lee7s.shop.back.service.ProductService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ProductIdAndNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@RequestMapping("/back/product")
public class ProductController {


    @Autowired
    private ProductService productService;


    /**
     * 获取产品分页数据
     * @param param
     * @return
     */
    @GetMapping("requestPageList")
    public R requestPageList(@RequestParam Map<String, Object> param){

        try{

            PageUtils productPageList = productService.requestProductPageList(param);

            return R.ok(REnum.GET_PRODUCT_PAGE_LIST_SUCCESS.getStatusCode(),
                            REnum.GET_PRODUCT_PAGE_LIST_SUCCESS.getStatusMsg())
                    .put("productPageList", productPageList);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GET_PRODUCT_PAGE_LIST_FAIL.getStatusCode(),
                    REnum.GET_PRODUCT_PAGE_LIST_FAIL.getStatusMsg());
        }
    }


    /**
     * 添加产品
     * @param product
     * @return
     */
    @PostMapping("appendProduct")
    public R appendProduct(@RequestBody Product product){

        try{

            R result = productService.appendProduct(product);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_APPEND_FAIL.getStatusCode(),
                    REnum.PRODUCT_APPEND_FAIL.getStatusMsg());
        }

    }

    /**
     * 修改产品
     * @param product
     * @return
     */
    @PutMapping("alterProduct")
    public R alterProduct(@RequestBody Product product){
        try{

            R result = productService.alterProduct(product);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_ALTER_FAIL.getStatusCode(),
                    REnum.PRODUCT_ALTER_FAIL.getStatusMsg());
        }
    }

    /**
     * 删除产品
     * @param id
     * @return
     */
    @DeleteMapping("removeProduct/{id}")
    public R removeProduct(@PathVariable Integer id){
        try{

            productService.removeProduct(id);

            return R.ok(REnum.PRODUCT_REMOVE_SUCCESS.getStatusCode(),
                    REnum.PRODUCT_REMOVE_SUCCESS.getStatusMsg());

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_REMOVE_FAIL.getStatusCode(),
                    REnum.PRODUCT_REMOVE_FAIL.getStatusMsg());


        }
    }


    /**
     * 修改产品上架状态
     * @param product
     * @return
     */
    @PutMapping("alterProductStatus")
    public R alterProductStatus(@RequestBody Product product){
        try{

            R result = productService.alterProductStatus(product);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_STATUS_ALTER_FAIL.getStatusCode(),
                    REnum.PRODUCT_STATUS_ALTER_FAIL.getStatusMsg());
        }
    }


    /**
     * 根据产品分类id获取可用产品id和名字
     * @return
     */
    @GetMapping("requestProductIdAndNameListByCategoryId/{productCategoryId}")
    public R requestProductIdAndNameListByCategoryId(@PathVariable Integer productCategoryId){
        try{

            List<ProductIdAndNameVo> productIdAndNameList = productService.requestProductIdAndNameListByCategoryId(productCategoryId);

            return R.ok(REnum.REQUEST_PRODUCT_ID_AND_NAME_SUCCESS.getStatusCode(),
                            REnum.REQUEST_PRODUCT_ID_AND_NAME_SUCCESS.getStatusMsg())
                    .put("productIdAndNameList", productIdAndNameList);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.REQUEST_PRODUCT_ID_AND_NAME_FAIL.getStatusCode(),
                    REnum.REQUEST_PRODUCT_ID_AND_NAME_FAIL.getStatusMsg());
        }
    }


}
