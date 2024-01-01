package com.lee7s.shop.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee7s.shop.back.entity.SysDict;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.AddSysDictVo;

import java.util.List;
import java.util.Map;

/**
 * @author somg
 * @date 2023/3/10 12:43
 * @do 系统字典服务接口
 */
public interface SysDictService extends IService<SysDict> {
    void addDict(AddSysDictVo addSysDictVo);

    List<SysDict> getAllParentDict();

    PageUtils selectDictByPage(Map<String, Object> param);

    R deleteDict(Long dictId);

    SysDict findDictByParentAndSelfCode(String parentDictCode, String sonDictCode);

    Map<String, String> findDictByParentCode(String parentCode);

    SysDict findDictBySonCode(String dictCode);
}