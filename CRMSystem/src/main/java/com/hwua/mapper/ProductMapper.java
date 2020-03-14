package com.hwua.mapper;

import com.hwua.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface ProductMapper {
    public List<Product> findAllProduct()throws Exception;
    public int addProduct(Product product)throws Exception;
    public int delProduct(String id)throws Exception;
    public int changeStatus(String id,String num)throws Exception;
    public Product findProductById(String id)throws Exception;
}
