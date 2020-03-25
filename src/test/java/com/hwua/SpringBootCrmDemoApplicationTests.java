package com.hwua;

import com.hwua.controller.UserController;
import com.hwua.mapper.OrdesMapper;
import com.hwua.pojo.Product;
import com.hwua.pojo.Users;
import com.hwua.service.OrdersService;
import com.hwua.service.ProductService;
import com.hwua.service.UserService;
import com.hwua.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@SpringBootTest
class SpringBootCrmDemoApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserController userController;
    @Test
    void contextLoads() throws Exception {
       Users user=new Users();
       user.setUsername("222");
       user.setPassword("222");
       user.setEmail("1112333@qq.com");
        System.out.println(userService.saveUser(user));
    }

    @Test
    void findUser()throws Exception{
        Users jack = userService.findUser("chenhao");
        System.out.println(jack.getRole());
    }






}
