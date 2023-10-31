package com.lee7s.shop.back.controller;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lee7s.shop.back.config.EmailCodeClient;
import com.lee7s.shop.back.service.OrderService;
import com.lee7s.shop.back.utils.AlipayTemplate;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.GoodsPayVo;
import com.lee7s.shop.back.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
@RestController
@RequestMapping("/back/order")
public class OrderController {

    @Autowired
    EmailCodeClient emailCodeClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayTemplate alipayTemplate;

//    /**
//     * 支付功能,整合alipay使用
//     * @param goodsPayVo
//     * @return 支付页面
//     * @throws AlipayApiException
//     */
//    @PostMapping(value = "pay", produces = "text/html")
//    @ResponseBody
//    public String orderPay(@RequestBody GoodsPayVo goodsPayVo) throws AlipayApiException {
//
//        PayVo payVo = orderService.constructOrderPayData(goodsPayVo);
//
//        String aliPayPage = alipayTemplate.pay(payVo);
//
//        // System.out.println(alipayTemplate.getAlipay_public_key());
//
//        return aliPayPage;
//    }


    /**
     * 支付功能,整合alipay使用
     * @param goodsPayVo
     * @return 支付页面
     * @throws AlipayApiException
     */
    @PostMapping(value = "pay")
    @ResponseBody
    public R orderPay(@RequestBody GoodsPayVo goodsPayVo) {

        return emailCodeClient.sendEmailToLee7s(goodsPayVo.getEmail(),
                orderService.constructOrderPayData(goodsPayVo) == null ?
                        goodsPayVo.getMoneyToken() + " 下单失败，及时联系客户退款"
                        : goodsPayVo.getMoneyToken());

    }

    @GetMapping(value = "pay")
    @ResponseBody
    public String orderPay() {

       emailCodeClient.sendEmailOrder("749062870@qq.com", IdWorker.get32UUID());

        return "ok";
    }


    public static void main(String[] args) {
        String e = "3";
        String[] split = e.split(":");
        System.out.println(split.length);
    }

}
