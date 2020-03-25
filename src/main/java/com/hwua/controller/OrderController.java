package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Orders;
import com.hwua.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private OrdersService ordersService;
    @GetMapping("findAllOrders/{pageNum}/{pageSize}")
    @ResponseBody
    public PageInfo<Orders> findAllOrders(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Orders> allOrders = ordersService.findAllOrders();
        PageInfo<Orders> pageInfo =new PageInfo<>(allOrders);
        return pageInfo;
    }

    @GetMapping("findOrderById/{id}")
    public String findOrderById(@PathVariable("id")String id, Map<String,Object> map){
        System.out.println("Controller====findOrderById");
        Orders orderById = ordersService.findOrderById(id);
        map.put("orders",orderById);
        return "pages/orders-show";
    }
    @DeleteMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(String orderNums){
        String[] split = orderNums.split(",");
        ordersService.deleteOrderById(split);
        return "order-page-list";
    }
    @PutMapping("/updateOrders")
    public String dateOrder(String productNums,String status){
        System.out.println(productNums+","+status);
        String[] split = productNums.split(",");
       ordersService.updateOrder(split,status);
       return null;
    }

}
