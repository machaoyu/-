package com.hwua.service.Impl;

import com.hwua.mapper.ProductMapper;
import com.hwua.pojo.Product;
import com.hwua.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findAllProduct() throws Exception {
        return productMapper.findAllProduct();
    }

    @Override
    public int addProduct(Product product) throws Exception {
        return productMapper.addProduct(product);
    }

    @Override
    public int delProduct(String id) throws Exception {
        return productMapper.delProduct(id);
    }

    @Override
    public int changeStatus(String id, String num) throws Exception {
        return productMapper.changeStatus(id, num);
    }

    @Override
    public Product findProductById(String id) throws Exception {
        return productMapper.findProductById(id);
    }
}
