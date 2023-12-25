package com.lee7s.shop.back.controller;

import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.ProductCategory;
import com.lee7s.shop.back.service.ProductCategoryService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.ProductCategoryIdAndNameVo;
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
@RequestMapping("/back/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;


    /**
     * 获取产品分类分页数据
     * @param param
     * @return
     */
    @GetMapping("requestPageList")
    public R requestPageList(@RequestParam Map<String, Object> param){

        try{

            PageUtils productCategoryPageList = productCategoryService.requestProductCategoryPageList(param);

            return R.ok(REnum.GET_PRODUCT_CATEGORY_PAGE_LIST_SUCCESS.getStatusCode(),
                            REnum.GET_PRODUCT_CATEGORY_PAGE_LIST_SUCCESS.getStatusMsg())
                    .put("productCategoryPageList", productCategoryPageList);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GET_PRODUCT_CATEGORY_PAGE_LIST_FAIL.getStatusCode(),
                    REnum.GET_PRODUCT_CATEGORY_PAGE_LIST_FAIL.getStatusMsg());
        }
    }


    /**
     * 添加产品分类
     * @param productCategory
     * @return
     */
    @PostMapping("appendProductCategory")
    public R appendProductCategory(@RequestBody ProductCategory productCategory){

        try{

            R result = productCategoryService.appendProductCategory(productCategory);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_CATEGORY_APPEND_FAIL.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_APPEND_FAIL.getStatusMsg());
        }

    }

    /**
     * 修改产品分类
     * @param productCategory
     * @return
     */
    @PutMapping("alterProductCategory")
    public R alterProductCategory(@RequestBody ProductCategory productCategory){
        try{

            R result = productCategoryService.alterProductCategory(productCategory);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_CATEGORY_ALTER_FAIL.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_ALTER_FAIL.getStatusMsg());
        }
    }

    /**
     * 删除产品分类
     * @param id
     * @return
     */
    @DeleteMapping("removeProductCategory/{id}")
    public R removeProductCategory(@PathVariable Integer id){
        try{

            productCategoryService.removeProductCategory(id);

            return R.ok(REnum.PRODUCT_CATEGORY_REMOVE_SUCCESS.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_REMOVE_SUCCESS.getStatusMsg());

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_CATEGORY_REMOVE_FAIL.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_REMOVE_FAIL.getStatusMsg());


        }
    }


    /**
     * 修改产品分类上架状态
     * @param productCategory
     * @return
     */
    @PutMapping("alterProductCategoryStatus")
    public R alterProductCategoryStatus(@RequestBody ProductCategory productCategory){
        try{

            R result = productCategoryService.alterProductCategoryStatus(productCategory);

            return result;

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.PRODUCT_CATEGORY_STATUS_ALTER_FAIL.getStatusCode(),
                    REnum.PRODUCT_CATEGORY_STATUS_ALTER_FAIL.getStatusMsg());
        }
    }


    /**
     * 请求可用的产品分类id和名字
     * @return
     */
    @GetMapping("requestProductCategoryIdAndNameList")
    public R requestProductCategoryIdAndNameList(){
        try{

            List<ProductCategoryIdAndNameVo> productCategoryIdAndNameList = productCategoryService.requestProductCategoryIdAndNameList();

            return R.ok(REnum.REQUEST_PRODUCT_CATEGORY_ID_AND_NAME_SUCCESS.getStatusCode(),
                            REnum.REQUEST_PRODUCT_CATEGORY_ID_AND_NAME_SUCCESS.getStatusMsg())
                    .put("productCategoryIdAndNameList", productCategoryIdAndNameList);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.REQUEST_PRODUCT_CATEGORY_ID_AND_NAME_FAIL.getStatusCode(),
                    REnum.REQUEST_PRODUCT_CATEGORY_ID_AND_NAME_FAIL.getStatusMsg());
        }
    }






}
