package com.lee7s.shop.back.service.mpl;

import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.service.OrderService;
import com.lee7s.shop.back.service.StatisticsService;
import com.lee7s.shop.back.vo.StatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee7s
 * @date 2023/12/28 12:41
 * @do 一数据统计实现
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单统计数据
     * @return
     */
    @Override
    public List<StatisticsVo> orderStatistics() {

        List<StatisticsVo> orderStatisticsList = new ArrayList<>();

        Integer lockOrderNum = orderService.statisticsOrderByStatus(Constant.OrderStatus.LOCK.getStatusCode());
        Integer finishOrderNum = orderService.statisticsOrderByStatus(Constant.OrderStatus.FINISH.getStatusCode());
        Integer cancelOrderNum = orderService.statisticsOrderByStatus(Constant.OrderStatus.CANCEL.getStatusCode());

        StatisticsVo lockOrder = new StatisticsVo();
        StatisticsVo finishOrder = new StatisticsVo();
        StatisticsVo cancelOrder = new StatisticsVo();

        lockOrder.setName(Constant.OrderStatus.LOCK.getStatusMsg());
        lockOrder.setValue(lockOrderNum);
        finishOrder.setName(Constant.OrderStatus.FINISH.getStatusMsg());
        finishOrder.setValue(finishOrderNum);
        cancelOrder.setName(Constant.OrderStatus.CANCEL.getStatusMsg());
        cancelOrder.setValue(cancelOrderNum);

        orderStatisticsList.add(lockOrder);
        orderStatisticsList.add(finishOrder);
        orderStatisticsList.add(cancelOrder);

        return orderStatisticsList;

    }
}
