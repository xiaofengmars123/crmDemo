package com.hwua.service;

import com.hwua.pojo.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAllProduct();
    public Integer addProduct(Product product);
    public Product findProductById(String id);
    public Integer deleteProductById(String[] id);
    public Integer updateProductsStatus(String [] ids,Integer status) throws Exception;
}
