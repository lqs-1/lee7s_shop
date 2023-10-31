package com.lee7s.shop.back.constant;

/**
 * @author somg
 * @date 2023/10/7 11:07
 * @do 各种常量
 */
public class Constant {

    // 订单支付自动收单时间
    public static final String ORDER_PAY_TIMEOUT_EXPIRE = "1m";

    // R对象中使用的常量
    public static final String RESPONSE_CODE_KEY = "code";
    // R对象中使用的常量
    public static final String RESPONSE_MSG_KEY = "msg";
    // 根字典
    public static final String  DICT_TYPE_PARENT = "0";
    // 子字典
    public static final String  DICT_TYPE_SON = "1";


    public enum OrderStatus{
        MODERATED(0, "待审核"),
        FINISH(1, "已完成"),
        CANCEL(2, "已取消");

        private Integer statusCode;

        private String statusMsg;

        OrderStatus(Integer statusCode, String statusMsg){
            this.statusCode = statusCode;
            this.statusMsg = statusMsg;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getStatusMsg() {
            return statusMsg;
        }
    }

    public enum GoodsStatus{
        AVAILABLE(0, "可用"),
        SOLD(1, "已售");

        private Integer statusCode;

        private String statusMsg;

        GoodsStatus(Integer statusCode, String statusMsg){
            this.statusCode = statusCode;
            this.statusMsg = statusMsg;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getStatusMsg() {
            return statusMsg;
        }
    }


}
