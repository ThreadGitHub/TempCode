package org.rabbit.producer.delayletter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.rabbit.utils.RabbitMQUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.TimeUnit;

/**
 * 死信队列实现延迟消息
 * @author thread
 * @date 2023/7/11 23:39
 */
@Slf4j
@SpringBootTest
public class DelayLetterProducer {
    @Autowired
    private RabbitMQUtils rabbitMQUtils;
    public static final String DELAY_QUEUE = "my_delay_queue";

    @Test
    public void test() {
        /**
         * rabbitMQ 死信队列实现延迟消息的问题
         * 第一个消息30秒过期  第二个消息15秒过期 第三个消息5秒过期
         * 因为30秒是第一个 即便后面加入的消息已经过期了也不会被消费 只有第一个30秒的过期了后面的才会被消费
         * 用延迟队列插件就不会有这个问题
         */
//        rabbitMQUtils.sendDealyMsg(DELAY_QUEUE, "延迟消息来了30秒", TimeUnit.SECONDS.toMillis(30));
//        log.info("延迟消息来了30秒 发送完成");
//
        rabbitMQUtils.sendDealyMsg(DELAY_QUEUE, "延迟消息来了15秒", TimeUnit.SECONDS.toMillis(15));
        log.info("延迟消息来了15秒 发送完成");

        rabbitMQUtils.sendDealyMsg(DELAY_QUEUE, "延迟消息来了5秒", TimeUnit.SECONDS.toMillis(5));
        log.info("延迟消息来了5秒 发送完成");
    }
}
