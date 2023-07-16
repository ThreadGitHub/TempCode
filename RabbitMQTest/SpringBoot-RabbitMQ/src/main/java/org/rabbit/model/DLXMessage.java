package org.rabbit.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 死信队列消息
 * @author thread
 * @date 2023/7/14 15:42
 */
@Getter
@Setter
@ToString
public class DLXMessage<T> implements Serializable {
    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 消息内容
     */
    private T message;
}
