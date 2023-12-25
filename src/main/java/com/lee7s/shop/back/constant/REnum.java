package com.lee7s.shop.back.constant;

/**
 * @author somg
 * @date 2023/1/16 12:15
 * @do 响应状态码枚举
 */
public enum REnum {


    // 通用成功失败状态码
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),


    // 没有权限 Security模块状态码 除了登录验证码以外都在这里
    NO_AUTH(401, "没有权限"),
    NO_LOGIN(402, "没有登录"),
    LOGOUT_SUCCESS_E(1000, "注销成功"),

    // 业务需求状态码
    WEB_FILE_GENERATE_SUCCESS(10000, "网页文件生成成功"),
    WEB_FILE_GENERATE_FAIL(20000, "网页文件生成失败"),
    NETWORK_CONNECT_FAIL(20001, "网络连接失败"),
    WEB_FILE_SINGLE_DELETE_SUCCESS(10003,"网页文件单个文件删除成功"),
    WEB_FILE_SINGLE_DELETE_FAIL(20003,"网页文件单个文件删除失败"),
    WEB_FILE_MULTI_DELETE_SUCCESS(10004,"网页文件批量文件删除成功"),
    WEB_FILE_MULTI_DELETE_FAIL(20004,"网页文件批量文件删除失败"),
    LOGIN_SUCCESS(11001, "登录成功"),
    LOGIN_FAIL(21010, "登录失败 用户名或者密码错误"),



    GET_PRODUCT_CATEGORY_PAGE_LIST_SUCCESS(11013, "获取产品分类列表成功"),
    GET_PRODUCT_CATEGORY_PAGE_LIST_FAIL(21019, "获取产品分类列表失败"),
    PRODUCT_CATEGORY_EXIST(21020, "产品分类已存在"),
    PRODUCT_CATEGORY_APPEND_SUCCESS(11021, "产品分类添加成功"),
    PRODUCT_CATEGORY_APPEND_FAIL(21021, "产品分类添加失败"),

    PRODUCT_CATEGORY_ALTER_SUCCESS(11022, "产品分类修改成功"),
    PRODUCT_CATEGORY_ALTER_FAIL(21022, "产品分类修改失败"),
    PRODUCT_CATEGORY_REMOVE_SUCCESS(11023, "删除产品分类成功"),
    PRODUCT_CATEGORY_REMOVE_FAIL(21023, "删除产品分类失败"),



    GET_PRODUCT_PAGE_LIST_SUCCESS(11024, "获取产品列表成功"),
    GET_PRODUCT_PAGE_LIST_FAIL(21024, "获取产品列表失败"),
    PRODUCT_EXIST(21025, "产品已存在"),
    PRODUCT_APPEND_SUCCESS(11026, "产品添加成功"),
    PRODUCT_APPEND_FAIL(21026, "产品添加失败"),

    PRODUCT_ALTER_SUCCESS(11027, "产品修改成功"),
    PRODUCT_ALTER_FAIL(21027, "产品修改失败"),
    PRODUCT_REMOVE_SUCCESS(11028, "删除产品成功"),
    PRODUCT_REMOVE_FAIL(21028, "删除产品失败"),

    GET_GOODS_PAGE_LIST_SUCCESS(11029, "获取商品列表成功"),
    GET_GOODS_PAGE_LIST_FAIL(21030, "获取商品列表失败"),
    GOODS_EXIST(21031, "商品已存在"),
    GOODS_APPEND_SUCCESS(11032, "商品添加成功"),
    GOODS_APPEND_FAIL(21032, "商品添加失败"),

    GOODS_ALTER_SUCCESS(11034, "商品修改成功"),
    GOODS_ALTER_FAIL(21034, "商品修改失败"),
    GOODS_REMOVE_SUCCESS(11035, "删除商品成功"),
    GOODS_REMOVE_FAIL(21035, "删除商品失败"),

    PRODUCT_STATUS_ALTER_SUCCESS(11036, "产品上架状态切换成功"),
    PRODUCT_STATUS_ALTER_FAIL(21036, "产品上架状态切换失败"),

    PRODUCT_CATEGORY_STATUS_ALTER_SUCCESS(11038, "产品分类上架状态切换成功"),
    PRODUCT_CATEGORY_STATUS_ALTER_FAIL(21038, "产品分类上架状态切换失败"),


    REQUEST_PRODUCT_CATEGORY_ID_AND_NAME_SUCCESS(11037, "获取可用产品分类信息成功"),
    REQUEST_PRODUCT_CATEGORY_ID_AND_NAME_FAIL(21037, "获取可用产品分类信息失败"),







    AUTH_FAIL(21024, "认证失败"),
    EMAIL_VALIDATE_CODE_SEND_SUCCESS(11033, "邮箱验证码发送成功,有效时间5分钟"),
    EMAIL_VALIDATE_CODE_SEND_FAIL(21033, "邮箱验证码发送失败"),

    EMAIL_SEND_TO_LEE7S_SUCCESS(10053, "发送邮件给lee7s成功"),
    EMAIL_SEND_TO_LEE7S_FAIL(20053, "发送邮件给lee7s失败"),

    EMAIL_SEND_ORDER_SUCCESS(10054, "订单号邮件发送成功"),
    EMAIL_SEND_ORDER_FAIL(20054, "订单号邮件发送失败"),



    ;

    private Integer statusCode;

    private String statusMsg;

    REnum(Integer statusCode, String statusMsg){
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
