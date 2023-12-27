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
@ConfigurationProperties(prefix = "email.lee7s")
public class EmailProperties {

    private String formEmail; // 发送邮件的邮箱

    private String formEmailNickName; // 发送邮件的邮箱的昵称


}
