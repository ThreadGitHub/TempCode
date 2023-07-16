package org.rabbit.consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.rabbit.constant.QueueConstant;
import org.rabbit.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * @author thread
 * @date 2023/7/8 17:56
 */
public class WorkQueueAckConsumer {
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();

        // 消费消息
        DeliverCallback deliverCallback = (String consumerTag, Delivery message) -> {
            System.out.println("消费消息：" + new String(message.getBody()));

            System.out.println("消息处理中。。。。");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 应答消息 消费成功
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            System.out.println("消费应答完成");
        };

        CancelCallback cancelCallback = (String consumerTag) -> {
            System.out.println("消息消费被中断：" + consumerTag);
        };

        // 消费消息
        // 1.队列名字
        // 2.是否自动应答
        // 3.消费成功回调
        // 4.消费中断回调
        boolean autoAck = false;
        channel.basicConsume(QueueConstant.WORK_QUEUE, autoAck, deliverCallback, cancelCallback);
    }
}
