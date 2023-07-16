package org.rabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;
import org.rabbit.constant.QueueConstant;
import org.rabbit.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * 异步发布确认
 * @author thread
 * @date 2023/7/9 19:54
 */
public class ConfirmAsyncProducer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();
        // 开启发布确认
        channel.confirmSelect();

        // 消息保存成功的确认
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("消息发布成功 " + deliveryTag);
        };

        // 消息发布未成功
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("消息发布未成功 " + deliveryTag);
        };

        // 增加异步确认监听器
        channel.addConfirmListener(ackCallback, nackCallback);

        // 创建队列
        // 1.队列名
        // 2.是否持久化磁盘
        // 3.是否多个消费者消费 false只能一个消费者消费
        // 4.是否自动删除
        // 5.其他参数
        channel.queueDeclare(QueueConstant.WORK_QUEUE, true, false, false, null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            // 发送消息
            // 1.交换器名称 空字符串默认
            // 2.队列名
            // 3.其他参数  持久化 PERSISTENT_TEXT_PLAIN-消息文本持久化到磁盘
            // 4.消息内容
            channel.basicPublish("", QueueConstant.WORK_QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, next.getBytes());
        }
    }
}
