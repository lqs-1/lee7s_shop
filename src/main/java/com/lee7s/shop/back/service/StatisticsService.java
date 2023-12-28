package com.lee7s.shop.back.service;

import com.lee7s.shop.back.vo.StatisticsVo;

import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/28 12:40
 * @do 统计数据接口
 */
public interface StatisticsService {
    List<StatisticsVo> orderStatistics();
}
