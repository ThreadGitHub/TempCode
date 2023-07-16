package org.rabbit.producer.workqueue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * work-queue 模型生产者
 * @author thread
 * @date 2023/7/11 20:26
 */
@SpringBootTest
public class WorkQueueProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void workQueue() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("boot_work", "work-" + i);
        }
    }
}
