package org.rabbit.routing.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.rabbit.routing.constant.QueueConstant;
import org.rabbit.routing.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * Routing模型 生产者
 * @author chugp
 * @date 2023/7/10 20:34
 */
public class RoutingProducer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();
        // 创建 direct 交换器
        channel.exchangeDeclare(QueueConstant.DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        // 发送消息
        String routingKey = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入routingKey:");
        routingKey = scanner.next();
        System.out.println("请发送消息：");
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(QueueConstant.DIRECT_EXCHANGE, routingKey, null, message.getBytes());
            System.out.println("发送成功");
        }
    }
}
