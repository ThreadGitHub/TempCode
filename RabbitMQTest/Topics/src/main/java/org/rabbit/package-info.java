package org.rabbit;
/**
 * topic模型
 * 跟Routing模式很像 也是通过交换器 routingkey 匹配队列 但是可以支持模糊匹配而不是固定值
 * 生产者 -> exchange(topic类型) -> 队列(模糊匹配routingKey) -> 消费者
 */