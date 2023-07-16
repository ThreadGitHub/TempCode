package org.rabbit;

/**
 * Routing 模型
 * 交换器根据队列绑定的routingkey 发送给不同的消费者
 * 消息 -> exchange(交换器) -> 队列绑定routingKey -> 消费者
 */