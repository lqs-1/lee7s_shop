package com.lee7s.shop.back.vo;

import lombok.Data;

import java.util.HashMap;

/**
 * @author somg
 * @date 2023/10/7 9:32
 * @do v免签视图对象
 */
@Data
public class VPayVo {


    private String out_trade_no; // 订单号
    private String payMethod; // 支付方式 1微信 2支付宝
    private String total_amount; // 支付金额
    private String sign; // 签名(payId+param+type+price+通讯密钥)
    private String param; // 传输参数，将会原样返回到异步和同步通知接口
    private String isHtml; // 1为返回支付页面 其他都是返回json
    private String notifyUrl; // 传入则设置该订单的异步通知接口为该参数，不传或传空则使用后台设置的接口
    private String returnUrl; // 传入则设置该订单的同步跳转接口为该参数，不传或传空则使用后台设置的接口


    public HashMap<String, String> constructPathQueryParameter(){
        HashMap<String, String> pathQueryParameterMap = new HashMap<>();

        pathQueryParameterMap.put("payId", this.getOut_trade_no());
        pathQueryParameterMap.put("type", this.getPayMethod());
        pathQueryParameterMap.put("price", this.getTotal_amount());
        pathQueryParameterMap.put("sign", this.getSign());
        pathQueryParameterMap.put("param", this.getParam());
        pathQueryParameterMap.put("isHtml", this.getIsHtml());
        pathQueryParameterMap.put("notifyUrl", this.getNotifyUrl());
        pathQueryParameterMap.put("returnUrl", this.getReturnUrl());

        return pathQueryParameterMap;
    }



}
