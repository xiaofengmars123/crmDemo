package com.hwua.mapper;

import com.hwua.pojo.Orders;
import com.hwua.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProductMapper {
    public List<Product> findAllProduct();
    public Product findProductById(String id);
    public Integer addProduct(Product product);
    public Integer deleteProductById(String id);
    public Integer updateProductsStatus(@Param("ids") String [] ids, @Param("status") Integer status) throws Exception;
}
