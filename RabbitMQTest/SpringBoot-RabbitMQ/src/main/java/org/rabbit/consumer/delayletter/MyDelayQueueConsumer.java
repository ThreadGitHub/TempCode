package org.rabbit.consumer.delayletter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 测试延迟队列 延迟效果
 * @author thread
 * @date 2023/7/14 16:20
 */
@Slf4j
@Component
@RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue("my_delay_queue"),
                exchange = @Exchange(value = "default_exchange")
        )
})
public class MyDelayQueueConsumer {
    @RabbitHandler
    public void consumer(String message) {
        log.info("MyDelayQueueConsumer 消费延迟消息: {}", message);
    }
}
