package com.example.order.controller;

import com.example.common.annotation.WebLog;
import com.example.common.entity.Order;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrderController
 * @Author yanyu
 * @Date 2024/4/25 16:05
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @WebLog
    @PostMapping("/getOrder")
    public Order getOrderByOid(@RequestBody Order order) {
        return orderService.getOrderByOid(order.getId());
    }

}
