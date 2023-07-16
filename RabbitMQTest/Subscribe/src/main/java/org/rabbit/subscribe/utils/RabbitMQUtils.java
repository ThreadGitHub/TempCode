package org.rabbit.subscribe.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author thread
 * @date 2023/7/8 17:46
 */
public class RabbitMQUtils {
    /**
     * 获取channel
     * @return
     */
    public static Channel getChannel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");
        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
