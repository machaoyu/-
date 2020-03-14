package com.hwua.service.Impl;

import com.hwua.mapper.OrderMapper;
import com.hwua.pojo.Orders;
import com.hwua.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
@Autowired
private OrderMapper orderMapper;
    @Override
    public List<Orders> findAllOrder() throws Exception {
        return orderMapper.findAllOrder();
    }

    @Override
    public Orders findOrderById(String id) throws Exception {
        return orderMapper.findOrderById(id);
    }
}
