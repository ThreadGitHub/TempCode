package org.rabbit.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 发布确认实现类
 * @author thread
 * @date 2023/7/16 00:38
 */
@Slf4j
@Component
public class RabbitConfirmCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造器注入自身实例对象到 rabbitTemplate中
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * ConfirmCallback
     * 交换机 成功和失败 接收消息的处理
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (correlationData != null) {
            if (ack) {
                System.out.println("交换机接收消息成功 id: " + correlationData.getId());
            } else {
                System.out.println("交换机接收消息失败 id: " + correlationData.getId() + " 失败原因:" + cause);
            }
        }
    }

    /**
     * ReturnsCallback
     * 消息回退的处理
     * @param returned
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println("回退消息 交换机: " + returned.getExchange() + ", routingKey: "+ returned.getRoutingKey() + ", message: " + returned.getMessage());
    }
}
