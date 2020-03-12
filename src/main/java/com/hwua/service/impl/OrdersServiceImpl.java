package com.hwua.service.impl;

import com.hwua.mapper.OrdesMapper;
import com.hwua.pojo.Orders;
import com.hwua.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdesMapper ordesMapper;
    @Override
    public List<Orders> findAllOrders() {
        return ordesMapper.findAllOrders();
    }

    @Override
    public Orders findOrderById(String id) {
        return ordesMapper.findOrderById(id);
    }

    @Override
    public Integer deleteOrderById(String[] id) {
        for(int i=0;i<id.length;i++){
            String x=id[i];
            System.out.println(x);
            ordesMapper.deleteOrderById(x);
        }
        return null;
    }

}
