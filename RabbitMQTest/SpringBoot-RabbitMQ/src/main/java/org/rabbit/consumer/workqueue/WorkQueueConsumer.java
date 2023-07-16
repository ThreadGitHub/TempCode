package org.rabbit.consumer.workqueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * work-queue 模型消费者
 * @author thread
 * @date 2023/7/11 20:23
 */
@Component
public class WorkQueueConsumer {
    @RabbitListener(queuesToDeclare = @Queue("boot_work"))
    public void consumer(String message) {
        System.out.println("consumer - 消费消息 " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("boot_work"))
    public void consumer2(String message) {
        System.out.println("consuemr2 - 消费消息 " + message);
    }
}
