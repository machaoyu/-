package com.hwua.service;

import com.hwua.pojo.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProduct()throws Exception;
    public int addProduct(Product product)throws Exception;
    public int delProduct(String id)throws Exception;
    public int changeStatus(String id,String num)throws Exception;
    public Product findProductById(String id)throws Exception;
}
