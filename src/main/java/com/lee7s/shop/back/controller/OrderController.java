package com.lee7s.shop.back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lee7s.shop.back.config.EmailCodeClient;
import com.lee7s.shop.back.service.OrderService;
import com.lee7s.shop.back.utils.AlipayTemplate;
import com.lee7s.shop.back.utils.HttpClientUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.GoodsPayVo;
import com.lee7s.shop.back.vo.PayVo;
import com.lee7s.shop.back.vo.VPayVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
@Slf4j
@RestController
@RequestMapping("/back/order")
public class OrderController {

    @Autowired
    EmailCodeClient emailCodeClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayTemplate alipayTemplate;

    /**
     * 支付功能,整合alipay使用
     * @param goodsPayVo
     * @return 支付页面
     * @throws AlipayApiException
     */
//    @PostMapping(value = "pay", produces = "text/html")
//    @ResponseBody
//    public String orderPay(@RequestBody GoodsPayVo goodsPayVo) throws AlipayApiException {
//
//        PayVo payVo = orderService.constructOrderPayDataAliPay(goodsPayVo);
//
//        String aliPayPage = alipayTemplate.pay(payVo);
//
//        // System.out.println(alipayTemplate.getAlipay_public_key());
//
//        return aliPayPage;
//    }


    /**
     * v免签支付
     * @param goodsPayVo
     * @return
     * @throws Exception
     */

    // https://shop.nobibibi.top/createOrder
    @PostMapping(value = "pay")
    @ResponseBody
    public String orderPay(@RequestBody GoodsPayVo goodsPayVo) throws Exception {

        VPayVo payVo = orderService.constructOrderPayDataVPay(goodsPayVo);

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("payId", payVo.getOut_trade_no());
        stringStringHashMap.put("type", payVo.getPayMethod());
        stringStringHashMap.put("price", payVo.getTotal_amount());
        stringStringHashMap.put("sign", payVo.getSign());
        stringStringHashMap.put("isHtml", payVo.getIsHtml());
        stringStringHashMap.put("notifyUrl", payVo.getNotifyUrl());
        stringStringHashMap.put("returnUrl", payVo.getReturnUrl());

        HttpResponse httpResponse = HttpClientUtils.doGet("https://shop.nobibibi.top/", "createOrder", new HashMap<>(), stringStringHashMap);

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = httpResponse.getEntity();
            String respString = EntityUtils.toString(entity);
            HashMap<String, String> responseMap = JSON.parseObject(respString, new TypeReference<HashMap<String, String>>(){});
            if (responseMap.get("code").equals("1")){
                HashMap<String, String> resultMap = JSON.parseObject(responseMap.get("data"), new TypeReference<HashMap<String, String>>(){});
                String orderId = resultMap.get("orderId");

                return "https://shop.nobibibi.top/payPage/pay.html?orderId=" + orderId;


            }

        }

        return "localhost:8080";
    }

//    @PostMapping("notify")
//    public void notify(HttpServletRequest request){
//        log.warn("post请求的notify");
//
//        // 获取所有参数的名称
//        Enumeration<String> parameterNames = request.getParameterNames();
//
//        // 遍历参数名称
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//
//            // 根据参数名称获取参数值
//            String paramValue = request.getParameter(paramName);
//            log.warn(paramName + " = " + paramValue);
//
//        }
//
//    }


    @GetMapping("notify")
    public void notify(HttpServletRequest request){
        log.warn("post请求的notify");

        // 获取所有参数的名称
        Enumeration<String> parameterNames = request.getParameterNames();

        // 遍历参数名称
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // 根据参数名称获取参数值
            String paramValue = request.getParameter(paramName);
            log.warn(paramName + " = " + paramValue);

        }

    }


    public static void main(String[] args) {
        String e = "3";
        String[] split = e.split(":");
        System.out.println(split.length);
    }

}
