package org.rabbit.consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.rabbit.constant.QueueConstant;
import org.rabbit.utils.RabbitMQUtils;
import java.io.IOException;

/**
 * 消费者
 * @author thread
 * @date 2023/7/8 17:56
 */
public class Consumer {
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();

        DeliverCallback deliverCallback = (String consumerTag, Delivery message) -> {
            System.out.println("消费消息：" + new String(message.getBody()));
        };

        CancelCallback cancelCallback = (String consumerTag) -> {
            System.out.println("消息消费被中断：" + consumerTag);
        };

        channel.basicConsume(QueueConstant.HELLO_WORLD_QUEUE, true, deliverCallback, cancelCallback);
    }
}
