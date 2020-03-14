package com.hwua.service;

import com.hwua.pojo.Orders;

import java.util.List;

public interface OrderService {
    public List<Orders> findAllOrder()throws Exception;
    public Orders findOrderById(String id)throws Exception;
}
