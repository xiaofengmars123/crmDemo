package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Product;
import com.hwua.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("findAllProduct/{pageNum}/{pageSize}")
    @ResponseBody
    public PageInfo<Product> findProductsByLimit(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productService.findAllProduct();
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    @ResponseBody
    @PostMapping("/addProduct")
    public ModelAndView addProduct(Product product){
        ModelAndView mv=new ModelAndView();
        Integer integer = productService.addProduct(product);
        mv.setViewName("product-list1");
        return mv;
    }


    @GetMapping("findOrdersByProductId/{id}")
    public String findOrdersByProductId(@PathVariable("id") String id, Map<String,Object> map){
        Product prouductById = productService.findProductById(id);
        map.put("product",prouductById);
        return "orders-list";
    }

    @DeleteMapping("/deleteProduct")
    @ResponseBody
    public String delectProduct(String productNums){

        String[] split = productNums.split(",");
        productService.deleteProductById(split);
        return "redirect:product-list1";
    }

    @PutMapping("/updataProduct")
    @ResponseBody
    public Map<String, Object> updateProductsByStatus(String productNums,Integer status) throws Exception {
        String[] ids = productNums.split(",");
        Integer res = productService.updateProductsStatus(ids,status);
        Map<String,Object> map = new HashMap<>();
        if(res>0){
            map.put("info","更新成功");
        }else{
            map.put("info","更新失败");
        }
        return map;
    }

}
