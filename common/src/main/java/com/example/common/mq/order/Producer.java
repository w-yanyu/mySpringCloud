package com.example.common.mq.order;

import com.example.common.mq.MqEntity;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Producer
 * @Author yanyu
 * @Date 2024/5/12 20:56
 * @Version 1.0
 * @Description TODO
 **/
//@Component
public class Producer {

    //@Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${mq.order.topic}")
    private String topic;

    @Value("${mq.order.group}")
    private String group;

    /**
     * 异步发送消息
     * @param o 消息
     */
    public void synConvertAndSend(Object o) {
        rocketMQTemplate.convertAndSend(topic,o);
    }

    /**
     * 异步发送消息
     * @param o 消息
     * @param callback 回调
     */
    public void asynConvertAndSend(Object o, SendCallback callback) {
        rocketMQTemplate.asyncSend(topic, o, callback);
    }
}
