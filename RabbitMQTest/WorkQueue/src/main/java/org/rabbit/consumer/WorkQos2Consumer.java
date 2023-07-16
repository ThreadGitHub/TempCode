package org.rabbit.consumer;

import com.rabbitmq.client.Channel;
import org.rabbit.constant.QueueConstant;
import org.rabbit.utils.RabbitMQUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author thread
 * @date 2023/7/9 17:36
 */
public class WorkQos2Consumer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        // 设置为不公平分发原则
        // A-处理很慢  B-处理很快  设置以后可以让不处于限制状态 因为默认分发原则是轮询
        channel.basicQos(1);

        channel.basicConsume(QueueConstant.WORK_QUEUE, false, (consumerTag, message)->{
            System.out.println("消费消息: " + new String(message.getBody()));

            // 模拟处理时间
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 消息回应处理完成
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        }, (consumerTag)->{
            System.out.println("消息处理中断");
        });
    }
}
