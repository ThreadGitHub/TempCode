package org.rabbit.consumer.alternate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 备份交换机消费者
 * @author thread
 * @date 2023/7/16 15:10
 */
@Slf4j
@Component
@RabbitListener(queues = "alternate_test_queue")
public class AlternateConsumer {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("备份交换机队列消费消息：" + message);
    }
}
