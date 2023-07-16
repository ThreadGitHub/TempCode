package org.rabbit.topics.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import org.rabbit.topics.constant.QueueConstant;
import org.rabbit.topics.utils.RabbitMQUtils;

/**
 * Topic 消费者
 * @author chugp
 * @date 2023/7/10 22:44
 */
public class TopicConsumer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

//        // 声明 topic 交换器
//        channel.exchangeDeclare(QueueConstant.TOPIC_EXCAHNGE, BuiltinExchangeType.TOPIC);

        // 创建临时消息
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("消息名称:" + queueName);

        // 绑定交换器 并指定routingkey
        // *-匹配单个单词 #-匹配多个单词
        String routingKey = "log.#";
        channel.queueBind(queueName, QueueConstant.TOPIC_EXCAHNGE, routingKey);

        // 消费消息
        channel.basicConsume(queueName, true, (consumerTag, message) -> {
            System.out.println("消费消息 " + new String(message.getBody()));
        }, (consumerTag) -> {
            System.out.println("消费中断 " + consumerTag);
        });
    }
}
