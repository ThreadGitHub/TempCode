package org.rabbit.routing.consumer;

import com.rabbitmq.client.Channel;
import org.rabbit.routing.constant.QueueConstant;
import org.rabbit.routing.utils.RabbitMQUtils;

/**
 * @author chugp
 * @date 2023/7/10 20:35
 */
public class RoutingDebugConsumer2 {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        // 创建临时消息
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, QueueConstant.DIRECT_EXCHANGE, "debug");

        // 消费消息
        channel.basicConsume(queueName, (consumerTag, message)->{
            System.out.println("消费消息 " + new String(message.getBody()));
        }, (consumerTag) -> {
            System.out.println("消费中断 " + consumerTag);
        });
    }
}
