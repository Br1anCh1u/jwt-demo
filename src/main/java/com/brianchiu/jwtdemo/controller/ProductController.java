package com.brianchiu.jwtdemo.controller;

import com.brianchiu.jwtdemo.dto.ProductQueryParams;
import com.brianchiu.jwtdemo.dto.ProductInsertRequest;
import com.brianchiu.jwtdemo.entity.Product;
import com.brianchiu.jwtdemo.service.ProductService;
import com.brianchiu.jwtdemo.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //filtering
            @RequestParam(required = false) String search,

            //sorting
            @RequestParam(defaultValue = "shelf_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //pagination
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //get product list
        List<Product> list = productService.getProducts(productQueryParams);

        //get total number
        Integer total = productService.countProduct(productQueryParams);

        //pagination
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(list);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products/insertProduct")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductInsertRequest productInsertRequest) {
        Integer productId = productService.createProduct(productInsertRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
