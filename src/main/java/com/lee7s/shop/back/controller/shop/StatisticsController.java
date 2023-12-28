package com.lee7s.shop.back.controller.shop;

import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.service.StatisticsService;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.StatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/28 12:33
 * @do 数据统计控制器
 */

@RestController
@RequestMapping("/back/statistics")
public class StatisticsController {


    @Autowired
    private StatisticsService statisticsService;


    /**
     * 订单统计
     * @return
     */
    @GetMapping("orderStatistics")
    public R orderStatistics(){

        try{

            List<StatisticsVo> statisticsVoList = statisticsService.orderStatistics();

            return R.ok(REnum.REQUEST_STATISTICS_ORDER_SUCCESS.getStatusCode(),
                    REnum.REQUEST_STATISTICS_ORDER_SUCCESS.getStatusMsg())
                    .put("orderStatisticsData", statisticsVoList);


        }catch (Exception e){

            return R.error(REnum.REQUEST_STATISTICS_ORDER_FAIL.getStatusCode(),
                    REnum.REQUEST_STATISTICS_ORDER_FAIL.getStatusMsg());

        }

    }





}
