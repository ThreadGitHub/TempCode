package org.rabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.rabbit.constant.QueueConstant;
import org.rabbit.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * 发布确认 生产者
 * @author thread
 * @date 2023/7/9 18:08
 */
public class ConfirmSelectProducer {
    public static void main(String[] args) throws Exception{
        // 获取信道
        Channel channel = RabbitMQUtils.getChannel();
        // 开启发布确认
        channel.confirmSelect();
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

            boolean result = channel.waitForConfirms();
            if (result) {
                System.out.println("发送成功");
            }
        }
    }
}
