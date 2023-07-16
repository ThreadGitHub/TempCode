package org.rabbit.consumer.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 延迟队列插件版 消费者
 * @author thread
 * @date 2023/7/11 22:24
 */
@Component
@Slf4j
@RabbitListener(
        queuesToDeclare = @Queue("boot_delay_test")
)
public class DelayConsumer {
    @RabbitHandler
    public void consumer(String message) {
        log.info("DelayConsumer-消费消息: {}", message);
    }
}
