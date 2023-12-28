package com.lee7s.shop.back.config;

import com.lee7s.shop.back.config.auto.EmailProperties;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.entity.Goods;
import com.lee7s.shop.back.entity.Order;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/**
 * @author : 李奇凇
 * @date : 2022/5/5 8:46
 * @do : 发送邮件工具类，一定要加到容器中在使用，如果直接使用这个工具类，会报空指针异常
 */

@Component
@Data
public class EmailCodeClient {

    @Autowired
    private JavaMailSender javaMailSender; // 邮件发送对像

    @Autowired
    private EmailProperties emailProperties; // 邮件的配置属性


    /**
     * 发送订单给用户
     * @param email
     * @param order
     * @param goodsList
     * @param reTry
     */
    public void sendEmailOrder(String email, Order order, List<Goods> goodsList, Integer reTry){
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        //需要借助Helper类
        MimeMessageHelper helper=new MimeMessageHelper(mailMessage);

        try {
            // 邮件标头
            helper.setFrom(emailProperties.getFormEmailNickName() + "<" + emailProperties.getFormEmail() + ">");
            // 发给谁
            helper.setTo(email);
            // 主题
            String subject = "感谢您购买: " + order.getProductName();
            helper.setSubject(subject);
            // 发送时间设置
            helper.setSentDate(new Date()); //发送时间
            // 要发送的文本
            StringBuffer context = new StringBuffer("<h5>订单号: " + order.getOrderSn() + "</h5>");
            context.append("您购买的商品如下:<br>");
            context.append("<hr>");

            if (goodsList.size() == 1){
                context.append("<p>" + goodsList.get(0).getGoodsDetail() + "</p>");
                context.append("<hr>");
            }else {
                for (Goods goods : goodsList) {
                    context.append("<p>" + goods.getGoodsDetail() + "</p>");
                    context.append("<hr>");
                }
            }
            context.append("<p>您也可以复制订单号到 " + Constant.ORDER_QUERY_URL + " 查询");

            // 这个是html格式
            helper.setText(context.toString(),true);
            //第一个参数要发送的内容，第二个参数不是Html格式。

            javaMailSender.send(mailMessage);

        }catch (Exception e) {
            e.printStackTrace();

            if (reTry == 5){
                return;
            }
            sendEmailOrder(email, order, goodsList, reTry + 1);
        }

    }



    /**
     * 发送订单给用户
     * @param email
     * @param order
     * @param goodsDetail
     * @param reTry
     */
    public void sendEmailOrder(String email, Order order, String goodsDetail, Integer reTry){
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        //需要借助Helper类
        MimeMessageHelper helper=new MimeMessageHelper(mailMessage);

        try {
            // 邮件标头
            helper.setFrom(emailProperties.getFormEmailNickName() + "<" + emailProperties.getFormEmail() + ">");
            // 发给谁
            helper.setTo(email);
            // 主题
            String subject = "感谢您购买: " + order.getProductName();
            helper.setSubject(subject);
            // 发送时间设置
            helper.setSentDate(new Date()); //发送时间
            // 要发送的文本
            StringBuffer context = new StringBuffer("<h5>订单号: " + order.getOrderSn() + "</h5>");
            context.append("您购买的商品如下:<br>");
            context.append("<hr>");


            context.append("<p>" + goodsDetail + "</p>");
            context.append("<hr>");

            context.append("<p>您也可以复制订单号到 " + Constant.ORDER_QUERY_URL + " 查询");

            // 这个是html格式
            helper.setText(context.toString(),true);
            //第一个参数要发送的内容，第二个参数不是Html格式。

            javaMailSender.send(mailMessage);

        }catch (Exception e) {
            e.printStackTrace();

            if (reTry == 5){
                return;
            }
            sendEmailOrder(email, order, goodsDetail, reTry + 1);
        }

    }


    /**
     * 传递一个标志位
     * @param email
     * @param emailCode
     * @param isHtml
     * @return
     */
//    public R sendEmailCode(String email, String emailCode, Boolean isHtml) {
//        if(isHtml){
//            return sendHtmlEmailCode(email, emailCode);
//        }
//        return sendSimpleEmailCode(email, emailCode);
//    }
//
//    /**
//     * 发送简单邮件(不带附件，不带格式)
//     * @param email
//     * @param emailCode
//     * @return
//     */
//    public R sendSimpleEmailCode(String email, String emailCode){
//        SimpleMailMessage message = new SimpleMailMessage();
//        // 加昵称版本
////        message.setFrom(new InternetAddress(MimeUtility.encodeText(formEmail)).toString());
//        message.setFrom(emailProperties.getFormEmailNickName() + "<" + emailProperties.getFormEmail() + ">");
//        // 不加昵称版本
//        // message.setFrom(formEmail);
//        message.setTo(email);
//        message.setSubject(emailProperties.getSubject());
//        message.setText(emailProperties.getSendValidateCodeContentPrefix() + emailCode);
//        // message.setCc("抄送人");
//        // message.setBcc("密送人");
//        try{
//            javaMailSender.send(message);
//            // 成功返回数据
//            return R.ok(REnum.EMAIL_VALIDATE_CODE_SEND_SUCCESS.getStatusCode(),
//                    REnum.EMAIL_VALIDATE_CODE_SEND_SUCCESS.getStatusMsg());
//        }catch (Exception e) {
//            e.printStackTrace();
//            // 失败返回错误信息
//            return R.error(REnum.EMAIL_VALIDATE_CODE_SEND_FAIL.getStatusCode(),
//                    REnum.EMAIL_VALIDATE_CODE_SEND_FAIL.getStatusMsg());
//        }
//    }
//
//
//    /**
//     * 发送带格式的邮件
//     * @param email
//     * @param emailCode
//     * @return
//     */
//    public R sendHtmlEmailCode(String email, String emailCode){
//        MimeMessage mailMessage = javaMailSender.createMimeMessage();
//        //需要借助Helper类 TODO 这个地方可以随便改
//        MimeMessageHelper helper=new MimeMessageHelper(mailMessage);
//        String context="<h3>尊敬的用户：</h3><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + email + "您好!<br><br>在您的验证码是:"+ emailCode +
//                "，请您于五分钟内使用。";
//        try {
//            helper.setFrom(emailProperties.getFormEmailNickName() + "<" + emailProperties.getFormEmail() + ">");
//            helper.setTo(email);
//            // helper.setBcc("密送人");
//            helper.setSubject(emailProperties.getSubject());
//            helper.setSentDate(new Date()); //发送时间
//            // 这个是html格式
//            helper.setText(context,true);
//            //第一个参数要发送的内容，第二个参数不是Html格式。
//
//            javaMailSender.send(mailMessage);
//            // 成功返回数据
//            return R.ok(REnum.EMAIL_VALIDATE_CODE_SEND_SUCCESS.getStatusCode(),
//                    REnum.EMAIL_VALIDATE_CODE_SEND_SUCCESS.getStatusMsg());
//        }catch (Exception e) {
//            e.printStackTrace();
//            // 失败返回错误信息
//            return R.error(REnum.EMAIL_VALIDATE_CODE_SEND_FAIL.getStatusCode(),
//                    REnum.EMAIL_VALIDATE_CODE_SEND_FAIL.getStatusMsg());
//        }
//
//    }
//
//
//
//    /**
//     * 发送简单邮件(不带附件，不带格式)
//     * @param email
//     * @param content
//     * @param subJect 主题
//     * @return
//     */
//    public void sendSimpleEmail(String email, String content, String subJect){
//        SimpleMailMessage message = new SimpleMailMessage();
//        // 加昵称版本
////        message.setFrom(new InternetAddress(MimeUtility.encodeText(formEmail)).toString());
//        message.setFrom(emailProperties.getFormEmailNickName() + "<" + emailProperties.getFormEmail() + ">");
//        // 不加昵称版本
//        // message.setFrom(formEmail);
//        message.setTo(email);
//        message.setSubject(subJect);
//        message.setText(content);
//        // message.setCc("抄送人");
//        // message.setBcc("密送人");
//        try{
//            javaMailSender.send(message);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//
//
//    /**
//     * 发送带格式的邮件
//     * @param email
//     * @param content
//     * @param subJect 主题
//     * @return
//     */
//    public void sendHtmlEmail(String email, String content, String subJect){
//        MimeMessage mailMessage = javaMailSender.createMimeMessage();
//        //需要借助Helper类 TODO 这个地方可以随便改
//        MimeMessageHelper helper=new MimeMessageHelper(mailMessage);
//
//        try {
//            helper.setFrom(emailProperties.getFormEmailNickName() + "<" + emailProperties.getFormEmail() + ">");
//            helper.setTo(email);
//            // helper.setBcc("密送人");
//            helper.setSubject(subJect);
//            helper.setSentDate(new Date()); //发送时间
//            // 这个是html格式
//            helper.setText(content,true);
//            //第一个参数要发送的内容，第二个参数不是Html格式。
//
//            javaMailSender.send(mailMessage);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


}