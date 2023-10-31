package com.lee7s.shop.back.config.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author somg
 * @date 2023/4/15 17:10
 * @do 发送邮件给商家的配置属性
 */

@Data
@Component
@ConfigurationProperties(prefix = "email.lee7s")
public class EmailLee7sProperties {

    private String formEmail = "lee7s.qs@gmail.com"; // 发送邮件的邮箱

    private String formEmailNickName = "Lee7sShop"; // 发送邮件的邮箱的昵称

    private String subject = "Lee7sShop订单信息"; // 发送邮件的主题

    private String sendContentPrefix = "用户:"; // 邮件内容前缀 非HTML邮件使用

    private String moneyTokenPrefix = "口令红包:"; // 红包口令


}
