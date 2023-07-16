/**
 * 备份交换机示例
 * 生产者 -> 正常交换机(设置了备份交换器属性,并且消息不可达没有该队列无人接收) -> 备份交换机(fanout类型) -> 备份交换机的队列消费
 * 如果备份交换机的类型不是 fanout 是不起作用的
 */
package org.rabbit.consumer.alternate;