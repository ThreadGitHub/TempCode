package org.rabbit.utils;

import lombok.extern.slf4j.Slf4j;
import org.rabbit.model.DLXMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMq
 * @author thread
 * @date 2023/7/14 15:24
 */
@Component
@Slf4j
public class RabbitMQUtils {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDealyMsg(String queueName, String message, long times) {
        DLXMessage<String> dlxMessage = new DLXMessage();
        dlxMessage.setMessage(message);
        dlxMessage.setQueueName(queueName);
        rabbitTemplate.convertAndSend("default_delay_letter_queue", dlxMessage, msg -> {
            msg.getMessageProperties().setExpiration(String.valueOf(times));
            return msg;
        });
    }
}
