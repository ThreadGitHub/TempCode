package org.rabbit.subscribe.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.rabbit.subscribe.constant.QueueConstant;
import org.rabbit.subscribe.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * 订阅模式生产者
 * @author chugp
 * @date 2023/7/10 20:05
 */
public class SubscribeProducer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        // 增加广播交换机
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);

        // 将消息发送给交换器 而不是 队列
        // 由交换器转发给每个消费者的队列
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(QueueConstant.SUBSCRIBE_EXCHANGE, "", null, message.getBytes());
            System.out.println("发送成功");
        }
    }
}
