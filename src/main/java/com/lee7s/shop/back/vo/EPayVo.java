package com.lee7s.shop.back.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee7s
 * @date 2023/12/31 13:18
 * @do 易支付视图对象
 */
@Data
public class EPayVo {

    private String p_id; // 商户id
    private String type; // 支付方式 alipay usdt
    private String out_trade_no; // 订单号
    private String notifyUrl; // 传入则设置该订单的异步通知接口为该参数，不传或传空则使用后台设置的接口
    private String returnUrl; // 传入则设置该订单的同步跳转接口为该参数，不传或传空则使用后台设置的接口
    private String name; // 商品名称
    private String money; // 订单总价
    private String clientip; // 用户发起支付方的ip
    private String device; // 用户设备类型
    private String param; // 没有请留空 支付后原样返回
    private String sign; // 签名(clientip={用户访问ip}&device={用户访问设备}&money={金额}&name={商品名称}&notify_url={服务器异步通知地址}&out_trade_no={商户订单号}&pid={商户ID}&return_url={页面跳转通知地址}&type={支付方式}{商户密钥})
    private String sign_type; // 签名类型 MD5


    public Map<String, String> constructPathQueryParameter() {

        HashMap<String, String> pathQueryParameterMap = new HashMap<>();

        pathQueryParameterMap.put("clientip", this.getClientip());
        pathQueryParameterMap.put("device", this.getDevice());
        pathQueryParameterMap.put("param", this.getParam());
        pathQueryParameterMap.put("pid", this.getP_id());
        pathQueryParameterMap.put("type", this.getType());
        pathQueryParameterMap.put("out_trade_no", this.getOut_trade_no());
        pathQueryParameterMap.put("notify_url", this.getNotifyUrl());
        pathQueryParameterMap.put("return_url", this.getReturnUrl());
        pathQueryParameterMap.put("name", this.getName());
        pathQueryParameterMap.put("money", this.getMoney());
        pathQueryParameterMap.put("sign", this.getSign());
        pathQueryParameterMap.put("sign_type", this.getSign_type());

        return pathQueryParameterMap;



    }
}
