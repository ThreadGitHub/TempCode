package org.rabbit.producer.routing;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Routing 模型生产者
 * @author thread
 * @date 2023/7/11 20:39
 */
@SpringBootTest
public class RoutingProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        rabbitTemplate.convertAndSend("boot_routing_exchange", "log.info", "info消息");
        rabbitTemplate.convertAndSend("boot_routing_exchange", "log.error", "error消息");
        rabbitTemplate.convertAndSend("boot_routing_exchange", "log.debug", "debug消息");
    }

}
