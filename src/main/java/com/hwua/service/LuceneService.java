package com.hwua.service;

import com.hwua.pojo.Product;

import java.io.IOException;
import java.util.List;

public interface LuceneService {
    void productInitLucene() throws IOException;
    List<Product> productSearchIndex(String productName) throws IOException;
}
