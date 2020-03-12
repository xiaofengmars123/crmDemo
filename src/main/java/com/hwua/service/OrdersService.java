package com.hwua.service;

import com.hwua.pojo.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAllOrders();
    public Orders findOrderById(String id);
    public Integer deleteOrderById(String[] id);
}
