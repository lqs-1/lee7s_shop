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
import com.lee7s.shop.back.vo.OrderPayVo;
import com.lee7s.shop.back.vo.OrderVo;
import com.lee7s.shop.back.vo.VPayVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
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

    @PostMapping(value = "pay")
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
    }


    /**
     * 支付完成之后异步通知接口 处理订单状态 以及自动发货 只有支付对应的金额才会来到这里 否则不来
     * @param request
     */
    @GetMapping("notify")
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
     * 根据订单号查询订单数据
     * @param orderSn
     * @return
     */
    @GetMapping("requestOrderByOrderSn/{orderSn}")
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
