package com.example.order.mq.consumer;

import com.example.common.entity.Order;
import com.example.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName Consumer
 * @Author yanyu
 * @Date 2024/5/12 20:56
 * @Version 1.0
 * @Description TODO
 **/
@Component
@RocketMQMessageListener(consumerGroup = "${mq.order.group}", topic = "${mq.order.topic}")
public class Consumer implements RocketMQListener<String> {

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(String o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Order> list = objectMapper.readValue(o, new TypeReference<List<Order>>() {});
            orderService.saveOrders(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
