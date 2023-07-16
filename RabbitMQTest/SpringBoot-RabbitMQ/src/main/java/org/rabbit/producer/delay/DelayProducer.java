package org.rabbit.producer.delay;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * 延迟队列插件 生产者
 * @author thread
 * @date 2023/7/11 22:28
 */
@SpringBootTest
public class DelayProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        // 发送延迟60秒消息
        rabbitTemplate.convertAndSend("boot_delay_exchange", "boot_delay_test", "延迟消息60秒",
                message -> {
                    message.getMessageProperties().setDelay((int)TimeUnit.SECONDS.toMillis(60));
                    return message;
                }
        );

        // 发送延迟30秒消息
        rabbitTemplate.convertAndSend("boot_delay_exchange", "boot_delay_test", "延迟消息30秒",
                message -> {
                    message.getMessageProperties().setDelay((int)TimeUnit.SECONDS.toMillis(30));
                    return message;
                }
        );

        // 发送延迟15秒消息
        rabbitTemplate.convertAndSend("boot_delay_exchange", "boot_delay_test", "延迟消息15秒",
                message -> {
                    message.getMessageProperties().setDelay((int)TimeUnit.SECONDS.toMillis(15));
                    return message;
                }
        );
    }
}
