package com.example.order.service;


import com.example.common.entity.Order;

import java.util.List;

public interface OrderService {
    Order getOrderByOid(String oid);

    void saveOrders(List<Order> orders);
}
