package org.rabbit.producer.confirm;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 发布确认生产者
 * @author thread
 * @date 2023/7/16 12:41
 */
@SpringBootTest
public class ConfirmProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        // 正常的逻辑
        String message = "confirm message ....";
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("confirm_01");
        rabbitTemplate.convertAndSend("confirm_exchange", "confirm_queue", message, correlationData);

        // 给不存在的交换机发消息
        // 失败原因:channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'confirm_exchange_xxxx' in vhost '/', class-id=60, method-id=40)
        String message2 = "confirm message 222 ....";
        CorrelationData correlationData2 = new CorrelationData();
        correlationData2.setId("confirm_02");
        rabbitTemplate.convertAndSend("confirm_exchange_xxxx", "confirm_queue", message2, correlationData2);

        // 给不存在的队列发消息
        // 回退消息 交换机: confirm_exchange, routingKey: confirm_queue_xxx, message: (Body:'confirm message 333 ....' MessageProperties [headers={spring_returned_message_correlation=confirm_03}, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])
        String message3 = "confirm message 333 ....";
        CorrelationData correlationData3 = new CorrelationData();
        correlationData3.setId("confirm_03");
        rabbitTemplate.convertAndSend("confirm_exchange", "confirm_queue_xxx", message3, correlationData3);
    }
}
