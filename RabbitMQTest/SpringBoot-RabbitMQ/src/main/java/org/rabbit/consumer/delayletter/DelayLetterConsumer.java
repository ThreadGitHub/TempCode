package org.rabbit.consumer.delayletter;

import lombok.extern.slf4j.Slf4j;
import org.rabbit.model.DLXMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 死信队列实现延迟消息
 * @author thread
 * @date 2023/7/11 23:37
 */
@Slf4j
@Component
@RabbitListener(
        queues = "default_delay_forward_letter_queue"
)
public class DelayLetterConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void consumer(DLXMessage message) {
        rabbitTemplate.convertAndSend(message.getQueueName(), message.getMessage());
        log.info("DelayLetterConsumer 转发消息: " + message);
    }
}
