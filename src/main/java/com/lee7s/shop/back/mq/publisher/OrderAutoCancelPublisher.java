package com.lee7s.shop.back.mq.publisher;

import com.lee7s.shop.back.constant.Constant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李奇凇
 * @moduleName OrderAutoCancelPublisher
 * @date 2023/1/11 下午1:23
 * @do 订单自动取消mq消息提供
 */

@Configuration
public class OrderAutoCancelPublisher<T> {


    @Autowired
    private RabbitTemplate rabbitClient;

    public void sendOrderCancelTimeDelayMessage(T message){

        /**
         * 发消息的时候写好发给哪个交换机，发给哪个route-key，要发送的消息就行了
         */
        rabbitClient.convertAndSend(Constant.ORDER_CANCEL_EXCHANGE, Constant.ORDER_CANCEL_TIME_DELAY_QUEUE_ROUTE_KEY, message);

    }

    /**
     * 创建交换机,这个交换机上面绑定一个延时队列和一个普通队列
     * 这个交换机用于绑定订单得延时队列和订单得死信队列   死信(延时队列过期得信息)
     * @return
     */
    @Bean
    public Exchange orderCancelExchange(){
        return new TopicExchange(Constant.ORDER_CANCEL_EXCHANGE, true, false);
    }

    /**
     * 创建库存得延时队列,配置项:
     *  这个队列和上面得交换机链接,只要订单保存成功就发送消息到这个队列
     *  延时到期绑定哪一个交换机
     *  延时到期绑定哪一个routeKey
     *  延时时间 毫秒
     *
     * @return
     */
    @Bean
    public Queue orderCancelTimeDelayQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Constant.ORDER_CANCEL_EXCHANGE);
        args.put("x-dead-letter-routing-key", Constant.ORDER_CANCEL_INVOKE_QUEUE_ROUTE_KEY);
        args.put("x-message-ttl", Constant.ORDER_CANCEL_TIME_DELAY_QUEUE_TTL);
        return new Queue(Constant.ORDER_CANCEL_TIME_DELAY_QUEUE, true, false, false, args);
    }

    /**
     * 创建普通队列,用于接收延时队列过期得消息, 接收死信,可以被监听到 然后做逻辑处理
     * @return
     */
    @Bean
    public Queue orderCancelInvokeQueue(){
        return new Queue(Constant.ORDER_CANCEL_INVOKE_QUEUE, true, false, false);
    }

    /**
     * 创建绑定关系:延时队列和交换机得关联 绑定交换机和延时队列
     * @return
     */
    @Bean
    public Binding orderCancelDelayQueueBinding(){
        return new Binding(Constant.ORDER_CANCEL_TIME_DELAY_QUEUE, Binding.DestinationType.QUEUE, Constant.ORDER_CANCEL_EXCHANGE, Constant.ORDER_CANCEL_TIME_DELAY_QUEUE_ROUTE_KEY, null);
    }

    /**
     * 创建绑定关系:普通队列和交换机得关联 绑定交换机和死信队列
     * @return
     */
    @Bean
    public Binding orderCancelInvokeQueueBinding(){
        return new Binding(Constant.ORDER_CANCEL_INVOKE_QUEUE, Binding.DestinationType.QUEUE, Constant.ORDER_CANCEL_EXCHANGE, Constant.ORDER_CANCEL_INVOKE_QUEUE_ROUTE_KEY, null);
    }




}
