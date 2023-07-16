package org.rabbit.producer.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * hello-world 模型-生产者
 * @author thread
 * @date 2023/7/11 20:12
 */
@SpringBootTest
public class HelloWorldProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void helloworld() {
        // 发送消息
        rabbitTemplate.convertAndSend("boot_hello-world", "hello world");
    }
}
