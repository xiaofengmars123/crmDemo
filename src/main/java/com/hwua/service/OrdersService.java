package com.hwua.service;

import com.hwua.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAllOrders();
    public Orders findOrderById(String id);
    public Integer deleteOrderById(String[] id);
    public Integer updateOrder( String[] id,String status);
}
