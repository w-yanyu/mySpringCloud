package com.example.common.mq;

import lombok.Data;

/**
 * @ClassName MqEntity
 * @Author yanyu
 * @Date 2024/5/12 20:52
 * @Version 1.0
 * @Description TODO
 **/
@Data
public class MqEntity {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息组
     */
    private String group;
}
