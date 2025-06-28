package com.example.order.service.impl;

import com.example.common.client.UserClient;
import com.example.common.entity.Order;
import com.example.common.entity.User;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Author yanyu
 * @Date 2024/4/25 16:16
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @Override
    public Order getOrderByOid(String oid) {
        Order order = orderMapper.selectById(oid);
        //User user = restTemplate.getForObject("http://user-service/user/getUser/"+order.getUserId(), User.class);
        User user = userClient.getUserById(order.getUserId());
        order.setUser(user);
        return order;
    }

    @Override
    public void saveOrders(List<Order> orders) {
        for(Order order:orders){
            orderMapper.insert(order);
        }
    }

}
