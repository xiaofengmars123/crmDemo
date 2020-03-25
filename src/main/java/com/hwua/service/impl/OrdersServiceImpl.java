package com.hwua.service.impl;

import com.hwua.mapper.OrdesMapper;
import com.hwua.pojo.Orders;
import com.hwua.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Integer deleteOrderById(String[] id) {
        for (int i = 0; i < id.length; i++) {
            String x = id[i];
            System.out.println(x);
            ordesMapper.deleteOrderById(x);
        }
        return null;
    }

    @Override
    public Integer updateOrder(String[] ids,String status) {
        System.out.println("service启动"+ids+";"+status);
        for (int i = 0; i < ids.length; i++) {
            String id = ids[i];
            System.out.println(id);
            ordesMapper.updateOrder(id,status);
        }
        return null;
    }
}
