package org.rabbit.consumer.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * fanout 发布订阅模型消费者
 * @author thread
 * @date 2023/7/11 20:33
 */
@Component
@RabbitListener(
        bindings = {
                @QueueBinding(
                        value = @Queue, // 不写value表示临时队列
                        exchange = @Exchange(value = "boot_fanout_exchange",  type = ExchangeTypes.FANOUT)
                )
        }
)
public class FanoutConsumer2 {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("FanoutConsumer2 消费消息: " + message);
    }
}
