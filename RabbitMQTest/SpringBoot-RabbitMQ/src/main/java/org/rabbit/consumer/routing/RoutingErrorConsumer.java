package org.rabbit.consumer.routing;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * routing 模型消费者
 * @author thread
 * @date 2023/7/11 20:44
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue, //不指定名字生成临时队列
                exchange = @Exchange(value = "boot_routing_exchange", type = ExchangeTypes.DIRECT),
                key = "log.error"
        )
)
public class RoutingErrorConsumer {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("RoutingErrorConsumer 消费消息: " + message);
    }
}
