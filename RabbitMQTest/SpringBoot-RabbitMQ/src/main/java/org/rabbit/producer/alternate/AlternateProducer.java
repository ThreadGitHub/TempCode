package org.rabbit.producer.alternate;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 备份交换机消息生产者
 * @author thread
 * @date 2023/7/16 15:11
 */
@SpringBootTest
public class AlternateProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        String message = "测试备份消息";
        rabbitTemplate.convertAndSend("test_exchange", "test_xxx", message);
    }
}
