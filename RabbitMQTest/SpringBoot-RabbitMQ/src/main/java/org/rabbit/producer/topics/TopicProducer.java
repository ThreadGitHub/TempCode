package org.rabbit.producer.topics;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * topic 动态路由模型
 * @author chugp
 * @date 2023/7/11 20:58
 */
@SpringBootTest
public class TopicProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("boot_topic_exchange", "log.123", "log.123 message");
            rabbitTemplate.convertAndSend("boot_topic_exchange", "log.456", "log.456 message");

            rabbitTemplate.convertAndSend("boot_topic_exchange", "log.456.info", "log.456.info message");
            rabbitTemplate.convertAndSend("boot_topic_exchange", "log.123.error", "log.456.info message");
        }
    }
}
