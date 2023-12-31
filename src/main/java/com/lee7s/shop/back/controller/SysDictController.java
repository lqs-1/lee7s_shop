package com.lee7s.shop.back.controller;

import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.SysDict;
import com.lee7s.shop.back.service.SysDictService;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.AddSysDictVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author somg
 * @date 2023/5/1 20:20
 * @do 系统字典控制器
 */
@RestController
@RequestMapping("/back/sysDict")
@Api(tags = "系统字典模块")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;


    /**
     * 展示字典，可以根据关键字
     * @param param
     * @return
     */
    @GetMapping("selectDict")
    public R selectDictByPage(@RequestParam Map<String, Object> param){
        try {
            PageUtils dictList = sysDictService.selectDictByPage(param);

            return R.ok(REnum.SELECT_DICT_LIST_SUCCESS.getStatusCode(),
                    REnum.SELECT_DICT_LIST_SUCCESS.getStatusMsg()).put("dictList", dictList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.SELECT_DICT_LIST_FAIL.getStatusCode(), REnum.SELECT_DICT_LIST_FAIL.getStatusMsg());
        }
    }




    /**
     * 添加字典
     * @param addSysDictVo
     * @return
     */
    @PostMapping("addDict")
    public R addDict(@RequestBody AddSysDictVo addSysDictVo){
        try {
            sysDictService.addDict(addSysDictVo);

            return R.ok(REnum.ADD_SYS_DICT_SUCCESS.getStatusCode(),REnum.ADD_SYS_DICT_SUCCESS.getStatusMsg());
        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.ADD_SYS_DICT_FAIL.getStatusCode(), REnum.ADD_SYS_DICT_FAIL.getStatusMsg());
        }
    }


    /**
     * 修改字典
     * @param sysDict
     * @return
     */
    @PostMapping("editDict")
    public R editDict(@RequestBody SysDict sysDict){
        try {
            sysDictService.updateById(sysDict);

            return R.ok(REnum.ALTER_DICT_SUCCESS.getStatusCode(),REnum.ALTER_DICT_SUCCESS.getStatusMsg());
        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.ALTER_DICT_FAIL.getStatusCode(), REnum.ALTER_DICT_FAIL.getStatusMsg());
        }
    }




    /**
     * 获取所有父级字典
     * @return
     */
    @GetMapping("parentSysDictList")
    public R parentSysDictList(){
        try {
            List<SysDict> sysDictList = sysDictService.getAllParentDict();

            return R.ok(REnum.GET_ALL_PARENT_DICT_SUCCESS.getStatusCode(),REnum.GET_ALL_PARENT_DICT_SUCCESS.getStatusMsg()).put("sysDictList", sysDictList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.GET_ALL_PARENT_DICT_FAIL.getStatusCode(), REnum.GET_ALL_PARENT_DICT_FAIL.getStatusMsg());
        }
    }


    /**
     * 删除字典
     * @return
     */
    @PostMapping("deleteDict/{dictId}")
    public R deleteDict(@PathVariable Long dictId){
        try {
            return sysDictService.deleteDict(dictId);

        }catch (Exception e){
            e.printStackTrace();
            return R.error(REnum.DICT_DELETE_FAIL.getStatusCode(), REnum.DICT_DELETE_FAIL.getStatusMsg());
        }
    }


}
