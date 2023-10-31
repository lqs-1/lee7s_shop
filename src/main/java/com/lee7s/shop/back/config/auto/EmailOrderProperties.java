package com.lee7s.shop.back.config.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author somg
 * @date 2023/4/15 17:10
 * @do 发送订单邮件给用户的配置属性
 */

@Data
@Component
@ConfigurationProperties(prefix = "email.order")
public class EmailOrderProperties {

    private String formEmail = "lee7s.qs@gmail.com"; // 发送邮件的邮箱

    private String formEmailNickName = "Lee7sShop"; // 发送邮件的邮箱的昵称

    private String subject = "Lee7sShop订单号"; // 发送邮件的主题

    private String sendOrderSnContentPrefix = "本网站在购买后，通过订单号查询订单商品，您的订单号:"; // 邮件内容前缀 非HTML邮件使用



}
