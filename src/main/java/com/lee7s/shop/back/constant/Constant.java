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


    // 单张网络图片生成目录
    public static final String WEB_FILE_SINGLE_GENERATE_DIR = "somgWebFileSingleGenerate";
    // 批量网络图片生成目录
    public static final String WEB_FILE_MULTI_GENERATE_DIR = "somgWebFileMultiGenerate";
    // 响应对象中的图片地址key
    public static final String WEB_FILE_GENERATE_SINGLE_SUCCESS_RESPONSE_NAME = "web-file-url";
    public static final String WEB_FILE_GENERATE_MULTI_SUCCESS_RESPONSE_NAME = "web-file-url-list";

    // 分页参数常量
    public static final String PAGE = "page";
    public static final String LIMIT = "limit";
    public static final String ORDERFILED = "orderFiled";
    public static final String ORDERTYPE = "orderType";


    public static final String ADMIN_NAME = "lee7s";
    public static final String ADMIN_PASSWORD = "lee7s";

    // headers允许前端访问的header字段名

    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";




    // v免签通讯密钥
    public static final String VKey = "2141d908d0ddf4f155107bc14fa37001";


    public enum OrderStatus{
        CREATE(0, "未支付"),
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


    /**
     * 产品分类上架状态
     */
    public enum ProductCategoryStatus{
        ON(0, "已上架"),
        OFF(1, "已下架");

        private Integer statusCode;

        private String statusMsg;

        ProductCategoryStatus(Integer statusCode, String statusMsg){
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
