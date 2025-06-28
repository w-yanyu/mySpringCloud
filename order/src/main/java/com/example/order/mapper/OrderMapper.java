package com.example.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName OrderMapper
 * @Author yanyu
 * @Date 2024/5/3 22:34
 * @Version 1.0
 * @Description TODO
 **/
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    // 批量插入订单信息
    int batchInsert(List<Order> orderList);
}
