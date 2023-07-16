package org.rabbit.topics.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.rabbit.topics.constant.QueueConstant;
import org.rabbit.topics.utils.RabbitMQUtils;

/**
 * Topic 生产者
 * @author chugp
 * @date 2023/7/10 22:41
 */
public class TopicProducer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        // 声明 Topic 交换器
        channel.exchangeDeclare(QueueConstant.TOPIC_EXCAHNGE, BuiltinExchangeType.TOPIC);

        // 发送消息
        String routingKey = "log.info";
        String message = routingKey;
        channel.basicPublish(QueueConstant.TOPIC_EXCAHNGE, routingKey, null, message.getBytes());

        // 发送消息
        String routingKey2 = "log.user.error";
        String message2 = routingKey2;
        channel.basicPublish(QueueConstant.TOPIC_EXCAHNGE, routingKey2, null, message2.getBytes());
        System.out.println("消息发送完成");
    }
}
