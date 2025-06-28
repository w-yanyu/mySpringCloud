package org.cloud.service.impl;

import com.example.common.entity.Order;
import com.example.common.mq.order.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaymentServiceImpl
 * @Author yanyu
 * @Date 2024/5/11 13:33
 * @Version 1.0
 * @Description TODO
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private Producer producer;
    @Override
    public boolean payOrder(String userid, List<String> goodIds) {
        List<Order> goods = new ArrayList<>();
        for(String goodId:goodIds){
            Order order = new Order();
            order.setUserId(userid);
            order.setProductName("商品编号："+goodId);
            order.setAmount(new BigDecimal(1000));
            goods.add(order);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            producer.synConvertAndSend(objectMapper.writeValueAsString(goods));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
