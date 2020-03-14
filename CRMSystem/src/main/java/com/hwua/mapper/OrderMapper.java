package com.hwua.mapper;

import com.hwua.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface OrderMapper {
    public List<Orders> findAllOrder()throws Exception;
    public Orders findOrderById(String id)throws Exception;
}
