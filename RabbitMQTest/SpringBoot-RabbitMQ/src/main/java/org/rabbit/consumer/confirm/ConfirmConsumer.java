package org.rabbit.consumer.confirm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author thread
 * @date 2023/7/16 12:36
 */
@Slf4j
@Component
@RabbitListener(
        bindings = {
                @QueueBinding(
                        value = @Queue("confirm_queue"),
                        exchange = @Exchange(value = "confirm_exchange"),
                        key = "confirm_queue"
                )
        }
)
public class ConfirmConsumer {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("ConfirmConsumer 消费消息：" + message);
    }
}
