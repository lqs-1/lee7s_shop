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
    public static final String DICT_TYPE_PARENT = "0";
    // 子字典
    public static final String DICT_TYPE_SON = "1";


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
    public static final String VKey = "2eb9e36bd3c22e41dd9c7b1c1315cd9b";

    // v免签地址
    public static final String VPayHOST = "https://shop.nobibibi.top/";
    // v免签创建订单接口
    public static final String VPayINTERFACE = "createOrder";

    // v免签支付页面
    public static final String VPayPAGE = "https://shop.nobibibi.top/payPage/pay.html?orderId=";
    // 易支付地址
    public static final String EASY_PAY_HOST = "https://epay.nobibibi.top/";
    // 易支付创建订单接口
    public static final String EASY_PAY_INTERFACE = "mapi.php";
    // 易支付商户id
    public static final String EASY_PID = "1001";
    // 易支付签名类型
    public static final String EASY_SIGN_TYPE = "MD5";
    // 易支付商户密钥
    public static final String EASY_KEY = "LxL1Il9NL9L5hxBXhtI3N57N1Nv9I71x";

    // 支付通知地址notifyUrl
    public static final String NOTIFY_URL = "http://nobibibi.top/back/order/notify";

    // 支付通知地址returnUrl
    public static final String RETURN_URL = "https://nobibibi.top/back/order/return";



    // 延时队列-订单交换机
    public static final String ORDER_CANCEL_EXCHANGE = "order-cancel-exchange";
    // 延时队列-订单取消延时队列
    public static final String ORDER_CANCEL_TIME_DELAY_QUEUE = "order-cancel-time-delay-queue";
    // 延时队列-订单取消延时队列routeKey
    public static final String ORDER_CANCEL_TIME_DELAY_QUEUE_ROUTE_KEY = "order-cancel-time-delay-queue-key";
    // 延时队列-订单取消执行队列
    public static final String ORDER_CANCEL_INVOKE_QUEUE = "order-cancel-invoke-queue";
    // 延时队列-订单取消执行routeKey
    public static final String ORDER_CANCEL_INVOKE_QUEUE_ROUTE_KEY = "order-cancel-invoke-queue-key";
    // 延时时间 这个时间用于自动取消订单 10分钟
    public static final Integer ORDER_CANCEL_TIME_DELAY_QUEUE_TTL = 600000;
    // 订单查询网址
    public static final String ORDER_QUERY_URL = "https://shop.somg.xyz/#/order";
    // 订单创建失败返回地址
    public static final String ORDER_CREATE_FAIL_RETURN_URL = "https://shop.somg.xyz";
    // 手动发货产品 发送给下面指定的客服邮箱 提醒发货
    public static final String MANUAL_ASSISTANT_MAIL_ADDRESS = "749062870@qq.com";
    // 手动发货产品 发送给客户邮箱的客服Telegram联系方式
    public static final String MANUAL_ASSISTANT_TELEGRAM_ADDRESS = "https://t.me/shnajkzl";
    // 手动发货产品 发送给客户邮箱的客服GMAIL联系方式
    public static final String MANUAL_ASSISTANT_GMAIL_ADDRESS = "liqisong2002@gmail.com";





    /**
     * 产品分类上架状态
     */
    public enum ProductCategoryStatus {
        ON(0, "已上架"),
        OFF(1, "已下架");

        private Integer statusCode;

        private String statusMsg;

        ProductCategoryStatus(Integer statusCode, String statusMsg) {
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
     * 产品上架状态
     */
    public enum ProductStatus {
        ON(0, "已上架"),
        OFF(1, "已下架");

        private Integer statusCode;

        private String statusMsg;

        ProductStatus(Integer statusCode, String statusMsg) {
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
     * 商品上架状态
     */
    public enum GoodsStatus {
        ON(0, "已上架"),
        OFF(1, "已下架");

        private Integer statusCode;

        private String statusMsg;

        GoodsStatus(Integer statusCode, String statusMsg) {
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
     * 商品状态
     */
    public enum GoodsGoodsStatus {
        ON(0, "可用"),
        OFF(1, "已售"),
        LOCK(2, "锁定");
        private Integer statusCode;

        private String statusMsg;

        GoodsGoodsStatus(Integer statusCode, String statusMsg) {
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
     * 订单状态
     */
    public enum OrderStatus {
        LOCK(0, "库存锁定,订单已创建,等待支付"),
        FINISH(1, "已完成"),
        CANCEL(2, "已取消");
        private Integer statusCode;

        private String statusMsg;

        OrderStatus(Integer statusCode, String statusMsg) {
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
     * 发货方式
     */
    public enum ProductMethod {
        AUTO(0, "自动发货"),
        MANUAL(1, "手动发货");
        private Integer statusCode;

        private String statusMsg;

        ProductMethod(Integer statusCode, String statusMsg) {
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
     * 支付方式
     */
    public enum PayMethod {
        TENCENT_PAY(1, "微信支付"),
        ALIPAY(2, "支付宝支付");
        private Integer statusCode;

        private String statusMsg;

        PayMethod(Integer statusCode, String statusMsg) {
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
     * 产品类型
     */
    public enum ProductType {
        HAS_STOCK(0, "有库存产品"),
        NO_STOCK(1, "无库存产品");
        private Integer statusCode;

        private String statusMsg;

        ProductType(Integer statusCode, String statusMsg) {
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
