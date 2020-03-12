package com.hwua.mapper;

import com.hwua.pojo.Orders;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrdesMapper {
    public List<Orders> findProductOreders(String id);
    public List<Orders> findAllOrders();
    public Orders findOrderById(String id);
    public Integer deleteOrderById(String id);
}
