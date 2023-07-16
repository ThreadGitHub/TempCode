package org.rabbit.consumer.helloworld;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * hello-world 模型消费者
 * @author thread
 * @date 2023/7/11 20:18
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("boot_hello-world"))
public class HelloWorldConsumer {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("消费消息 " + message);
    }
}
