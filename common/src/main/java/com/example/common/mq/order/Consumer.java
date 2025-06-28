package com.example.common.mq.order;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer
 * @Author yanyu
 * @Date 2024/5/12 20:56
 * @Version 1.0
 * @Description TODO
 **/
@Component
@RocketMQMessageListener(consumerGroup = "${mq.order.group}", topic = "${mq.order.topic}")
public class Consumer implements RocketMQListener<Object> {

    @Override
    public void onMessage(Object o) {
        System.out.println(o);
    }
}
