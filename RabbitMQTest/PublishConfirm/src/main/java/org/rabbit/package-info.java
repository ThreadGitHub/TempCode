package org.rabbit;
/**
 * 发布确认
 * 说明：发布确认即 生产者 -> 队列 之间为了确保消息不丢失的机制
 * rabbitMQ成功的将消息持久化后返回一个确认事件 如果未收到说明消息发送异常 生产者可以做异常处理
 * 1.单个消息发布确认
 * 2.批量消息发布确认
 * 3.异步消息发布确认
 */