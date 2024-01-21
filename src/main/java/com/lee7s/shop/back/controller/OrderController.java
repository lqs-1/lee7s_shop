package com.lee7s.shop.back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alipay.api.AlipayApiException;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.service.OrderService;
import com.lee7s.shop.back.utils.HttpClientUtils;
import com.lee7s.shop.back.utils.Pagination.PageUtils;
import com.lee7s.shop.back.utils.R;
import com.lee7s.shop.back.vo.EPayVo;
import com.lee7s.shop.back.vo.OrderPayVo;
import com.lee7s.shop.back.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee7s
 * @since 2023-10-07 10:33:14
 */
@Slf4j
@Controller
@RequestMapping("/back/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    // @Autowired
    // private AlipayTemplate alipayTemplate;



    /**
     * 获取订单分页数据
     * @param param
     * @return
     */
    @GetMapping("requestPageList")
    @ResponseBody
    public R requestPageList(@RequestParam Map<String, Object> param){

        try{

            PageUtils orderPageList = orderService.requestOrderPageList(param);

            return R.ok(REnum.GET_ORDER_PAGE_LIST_SUCCESS.getStatusCode(),
                            REnum.GET_ORDER_PAGE_LIST_SUCCESS.getStatusMsg())
                    .put("orderPageList", orderPageList);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.GET_ORDER_PAGE_LIST_FAIL.getStatusCode(),
                    REnum.GET_ORDER_PAGE_LIST_FAIL.getStatusMsg());
        }
    }



    /**
     * 支付功能,整合alipay使用
     * @param orderPayVo
     * @return 支付页面
     * @throws AlipayApiException
     */
/*    @PostMapping(value = "pay", produces = "text/html")
    @ResponseBody
    public String orderPay(@RequestBody OrderPayVo orderPayVo) throws AlipayApiException {

        PayVo payVo = orderService.constructOrderPayDataAliPay(orderPayVo);

        String aliPayPage = alipayTemplate.pay(payVo);

        // System.out.println(alipayTemplate.getAlipay_public_key());

        return aliPayPage;
    }*/


    /**
     * v免签支付
     * @param orderPayVo
     * @return
     * @throws Exception
     */

/*    @PostMapping(value = "pay")
    @ResponseBody
    public String orderPay(@RequestBody OrderPayVo orderPayVo) throws Exception {

        try {

            VPayVo payVo = orderService.constructOrderPayDataVPay(orderPayVo);

            HttpResponse httpResponse = HttpClientUtils.doGet(Constant.VPayHOST, Constant.VPayINTERFACE, new HashMap<>(), payVo.constructPathQueryParameter());

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                String respString = EntityUtils.toString(entity);
                HashMap<String, String> responseMap = JSON.parseObject(respString, new TypeReference<HashMap<String, String>>(){});
                if (responseMap.get("code").equals("1")){
                    HashMap<String, String> resultMap = JSON.parseObject(responseMap.get("data"), new TypeReference<HashMap<String, String>>(){});
                    String orderId = resultMap.get("orderId");
                    // 返回支付页面
                    return Constant.VPayPAGE + orderId;

                }

            }

            return Constant.ORDER_CREATE_FAIL_RETURN_URL;

        }catch (Exception e){
            e.printStackTrace();
            // 失败返回主页
            return Constant.ORDER_CREATE_FAIL_RETURN_URL;
        }
    }*/



    /**
     * 易支付支付
     * @param orderPayVo
     * @return
     * @throws Exception
     */

    @PostMapping(value = "pay")
    @ResponseBody
    public String orderPay(@RequestBody OrderPayVo orderPayVo, HttpServletRequest request) throws Exception {

        try {

            // 赠品订单
            if (orderPayVo.getGoodsPrice() < 0){

                orderService.constructFreeOrder(orderPayVo);

            }else {
                // 付费订单
                // 获取请求ip设备类型
                String ipAddress = request.getHeader("X-Forwarded-For");
                String device = "pc";

                if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getRemoteAddr();
                }

                // 如果是多级代理，获取第一个IP地址
                if (ipAddress != null && ipAddress.contains(",")) {
                    ipAddress = ipAddress.split(",")[0].trim();
                }
                System.out.println(ipAddress);

                // 获取设备类型
                String userAgent = request.getHeader("User-Agent");
                // 判断设备类型
                if (userAgent != null && !userAgent.isEmpty()) {
                    if (userAgent.toLowerCase().contains("mobile") || userAgent.toLowerCase().contains("android") || userAgent.toLowerCase().contains("iphone") || userAgent.toLowerCase().contains("ipad")) {
                        // 移动设备
                        device = "mobile";
                    }
                }


//            EPayVo ePayVo = orderService.constructOrderPayDataEPay(orderPayVo, "192.168.201.59", device);
                EPayVo ePayVo = orderService.constructOrderPayDataEPay(orderPayVo, ipAddress, device);

                HttpResponse httpResponse = HttpClientUtils.doPost(Constant.EASY_PAY_HOST, Constant.EASY_PAY_INTERFACE, new HashMap<>(), new HashMap<>(), ePayVo.constructPathQueryParameter());

                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = httpResponse.getEntity();
                    String respString = EntityUtils.toString(entity);
                    HashMap<String, String> responseMap = JSON.parseObject(respString, new TypeReference<HashMap<String, String>>(){});
                    if (responseMap.get("code").equals("1")){
                        // 返回支付页面
                        return responseMap.get("payurl");

                    }

                }
            }

            return Constant.ORDER_CREATE_FAIL_RETURN_URL;

        }catch (Exception e){
            e.printStackTrace();
            // 失败返回主页
            return Constant.ORDER_CREATE_FAIL_RETURN_URL;
        }
    }



    /**
     * 支付完成之后异步通知接口 处理订单状态 以及自动发货 只有支付对应的金额才会来到这里 否则不来
     * @param request
     */
    @GetMapping("notify")
    @ResponseBody
    public String notify(HttpServletRequest request){

        try {

            // 获取订单号
            String orderSn = request.getParameter("param");
            log.info("支付成功 订单号: " + orderSn);
            // 订单完成 发送邮件 修改订单状态
            orderService.completeOrder(orderSn);

            return "success";

        }catch (Exception e){
            e.printStackTrace();

            return "fail";
        }



    }

    /**
     * 支付成功的return回调
     * @return
     */
    @GetMapping("return")
    public String returnUrl(){

        return "redirect:https://shop.somg.xyz";

    }


    /**
     * 根据订单号查询订单数据
     * @param orderSn
     * @return
     */
    @GetMapping("requestOrderByOrderSn/{orderSn}")
    @ResponseBody
    public R requestOrderByOrderSn(@PathVariable String orderSn){

        try {
            OrderVo orderVo = orderService.requestOrderByOrderSn(orderSn);

            if (ObjectUtils.isEmpty(orderVo)){
                return R.error(REnum.USER_REQUEST_ORDER_FAIL.getStatusCode(),
                        REnum.USER_REQUEST_ORDER_FAIL.getStatusMsg());
            }

            return R.ok(REnum.USER_REQUEST_ORDER_SUCCESS.getStatusCode(),
                    REnum.USER_REQUEST_ORDER_SUCCESS.getStatusMsg())
                    .put("order", orderVo);

        }catch (Exception e){

            e.printStackTrace();

            return R.error(REnum.USER_REQUEST_ORDER_FAIL.getStatusCode(),
                    REnum.USER_REQUEST_ORDER_FAIL.getStatusMsg());
        }

    }


}
