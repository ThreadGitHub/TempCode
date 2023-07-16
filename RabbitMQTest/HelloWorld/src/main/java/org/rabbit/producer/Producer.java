package org.rabbit.producer;

import com.rabbitmq.client.Channel;
import org.rabbit.constant.QueueConstant;
import org.rabbit.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * 生产者
 * @author thread
 * @date 2023/7/8 17:45
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        // 创建队列
        // 1.队列名
        // 2.是否持久化磁盘
        // 3.是否多个消费者消费 false只能一个消费者消费
        // 4.是否自动删除
        // 5.其他参数
        channel.queueDeclare(QueueConstant.HELLO_WORLD_QUEUE, true, false, false, null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            // 发送消息
            // 1.交换器名称 空字符串默认
            // 2.队列名
            // 3.其他参数
            // 4.消息内容
            channel.basicPublish("", QueueConstant.HELLO_WORLD_QUEUE, null, next.getBytes());
            System.out.println("发送成功");
        }
    }
}
