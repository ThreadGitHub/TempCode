package org.rabbit.consumer.topics;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * topic 模型消费者
 * @author thread
 * @date 2023/7/11 20:58
 */
@Component
@RabbitListener(
        bindings = {
                @QueueBinding(
                        value = @Queue("boot_topic_queue"),  //创建队列
                        exchange = @Exchange(value = "boot_topic_exchange", type = ExchangeTypes.TOPIC),
                        key = "log.*" // 匹配单个词
                )
        }
)
public class TopicConsumer {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("TopicConsumer [log.*] 消费消息: " + message);
    }
}
