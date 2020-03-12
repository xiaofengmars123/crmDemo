package com.hwua.service.impl;

import com.hwua.mapper.ProductMapper;
import com.hwua.pojo.Product;
import com.hwua.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findAllProduct() {
        return productMapper.findAllProduct();
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public Product findProductById(String id) {
        return productMapper.findProductById(id);
    }

    @Override
    @Transactional
    public Integer deleteProductById(String[] id) {
        for(int i=0;i<id.length;i++){
                String x=id[i];
            System.out.println(x);
            productMapper.deleteProductById(x);
        }
        return null;
    }

    @Override
    public Integer updateProductsStatus(String[] ids, Integer status) throws Exception {
        return productMapper.updateProductsStatus(ids,status);
    }


}
