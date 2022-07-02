package com.brianchiu.jwtdemo.service.impl;

import com.brianchiu.jwtdemo.dao.ProductDao;
import com.brianchiu.jwtdemo.dto.ProductQueryParams;
import com.brianchiu.jwtdemo.dto.ProductRequest;
import com.brianchiu.jwtdemo.entity.Product;
import com.brianchiu.jwtdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
