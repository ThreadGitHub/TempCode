package org.rabbit.consumer.routing;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Routing 模型消费者
 * @author thread
 * @date 2023/7/11 20:38
 */
@Component
@RabbitListener(
    bindings = {
            @QueueBinding(
                    value = @Queue, //不指定名字为生成临时队列
                    exchange = @Exchange(value = "boot_routing_exchange", type = "direct"), // 默认也是direct
                    key = "log.debug"
            )
    }
)
public class RoutingDebugConsumer {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("RoutingDebugConsumer-消费消息: " + message);
    }
}
