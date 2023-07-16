package org.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * RabbbitMQ 配置类
 * @author thread
 * @date 2023/7/11 22:47
 */
@Configuration
public class RabbitMQConfig {
    /**
     * 延迟队列
     * @return
     */
    @Bean
    public Queue delayQueue() {
        return new Queue("boot_delay_test", true, false, false);
    }

    /**
     * 延迟队列
     * @return
     */
    @Bean
    public Queue delayQueue2() {
        return new Queue("boot_delay_test_2", true, false, false);
    }

    /**
     * 声明延迟队列插件交换机
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        // 设置延迟队列的交换器类型
//        args.put("x-delayed-type", "direct");
        // 设置延迟队列的交换机类型为 发布订阅模型
        args.put("x-delayed-type", "fanout");
        return new CustomExchange("boot_delay_exchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingDelay() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("boot_delay_test").noargs();
    }

    @Bean
    public Binding bindingDelay2() {
        return BindingBuilder.bind(delayQueue2()).to(delayExchange()).with("boot_delay_test_2").noargs();
    }

    /******* 死信队列实现延迟消息 *****/
    /**
     * 默认交换机
     * @return
     */
    @Bean
    public Exchange defaultExchange() {
        return new DirectExchange("default_exchange", true, false);
    }

    /**
     * 死信转发队列
     * @return
     */
    @Bean
    public Queue defaultDelayLetterQueue() {
        return new Queue("default_delay_forward_letter_queue", true, false, false);
    }
    /**
     * 绑定死信转发队列到默认交换机
     * @return
     */
    @Bean
    public Binding delayLetterBind() {
        return BindingBuilder.bind(defaultDelayLetterQueue())
                .to(defaultExchange()).with("default_delay_forward_letter_queue").noargs();
    }

    /**
     * 延迟消息死信队列
     * @return
     */
    @Bean
    public Queue delayLetterQueue() {
        HashMap<String, Object> args = new HashMap<>();
        // 设置死信交换机
        args.put("x-dead-letter-exchange", "default_exchange");
        // 死信转发队列
        args.put("x-dead-letter-routing-key", "default_delay_forward_letter_queue");
        // 设置全局消息的过期时间
//        args.put("x-message-ttl",10000);
        return new Queue("default_delay_letter_queue", true, false, false, args);
    }
    /**
     * 绑定延迟消息死信队列
     * @return
     */
    @Bean
    public Binding bindDelayLetterQueue() {
        return BindingBuilder.bind(delayLetterQueue())
                .to(defaultExchange())
                .with("default_delay_letter_queue")
                .noargs();
    }

    @Bean
    public Queue myDelayQueue() {
        return new Queue("my_delay_queue", true, false, false);
    }
    @Bean
    public Binding myDelayQueueBind() {
        return BindingBuilder.bind(myDelayQueue()).to(delayExchange()).with("my_delay_queue").noargs();
    }

    /******** 测试备份交换机 ******/
    @Bean
    public DirectExchange testExchange() {
        Map<String, Object> arguments = new HashMap<>();
        // 设置该交换机的备份交换机为  alternate_exchange
        arguments.put("alternate-exchange", "alternate_exchange");
        return new DirectExchange("test_exchange", true, false, arguments);
    }

    /**
     * 备份交换机
     * @return
     */
    @Bean
    public FanoutExchange alternateExchange() {
        return new FanoutExchange("alternate_exchange", true, false);
    }

    /**
     * 备份交换机的队列
     * @return
     */
    @Bean
    public Queue alternateTestQueue() {
        return new Queue("alternate_test_queue", true, false, false);
    }
    @Bean
    public Binding alternateTestBind() {
        return BindingBuilder.bind(alternateTestQueue()).to(alternateExchange());
    }
}
