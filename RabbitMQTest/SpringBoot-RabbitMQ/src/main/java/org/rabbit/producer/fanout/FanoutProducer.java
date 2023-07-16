package org.rabbit.producer.fanout;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * fanout 发布订阅模型 生产者
 * @author thread
 * @date 2023/7/11 20:30
 */
@SpringBootTest
public class FanoutProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            rabbitTemplate.convertAndSend("boot_fanout_exchange", "", "fanout-" + i);
        }
    }
}
