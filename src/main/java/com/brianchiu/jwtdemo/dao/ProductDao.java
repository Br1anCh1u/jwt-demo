package com.brianchiu.jwtdemo.dao;

import com.brianchiu.jwtdemo.dto.ProductQueryParams;
import com.brianchiu.jwtdemo.dto.ProductInsertRequest;
import com.brianchiu.jwtdemo.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductInsertRequest productInsertRequest);
}
