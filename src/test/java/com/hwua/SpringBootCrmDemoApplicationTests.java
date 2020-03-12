package com.hwua;

import com.hwua.mapper.OrdesMapper;
import com.hwua.pojo.Product;
import com.hwua.service.ProductService;
import com.hwua.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

@SpringBootTest
class SpringBootCrmDemoApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdesMapper ordesMapper;
    @Test
    void contextLoads() throws Exception {





    }




}
