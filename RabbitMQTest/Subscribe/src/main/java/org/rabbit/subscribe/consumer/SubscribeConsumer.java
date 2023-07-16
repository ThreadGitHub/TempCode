package org.rabbit.subscribe.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.rabbit.subscribe.constant.QueueConstant;
import org.rabbit.subscribe.utils.RabbitMQUtils;

/**
 * 发布订阅 消费者
 * @author chugp
 * @date 2023/7/10 20:13
 */
public class SubscribeConsumer {
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        // 声明交换机
        channel.exchangeDeclare(QueueConstant.SUBSCRIBE_EXCHANGE, BuiltinExchangeType.FANOUT);

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queueName,QueueConstant.SUBSCRIBE_EXCHANGE,"");

        // 消费消息
        channel.basicConsume(queueName, (consumerTag, message)->{
            System.out.println("消费消息 " + new String(message.getBody()));
        }, (consumerTag) -> {
            System.out.println("消费中断 " + consumerTag);
        });
    }
}
