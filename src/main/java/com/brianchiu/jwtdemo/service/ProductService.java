package com.brianchiu.jwtdemo.service;

import com.brianchiu.jwtdemo.dto.ProductQueryParams;
import com.brianchiu.jwtdemo.dto.ProductRequest;
import com.brianchiu.jwtdemo.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
