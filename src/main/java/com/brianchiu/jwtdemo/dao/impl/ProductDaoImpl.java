package com.brianchiu.jwtdemo.dao.impl;

import com.brianchiu.jwtdemo.dao.ProductDao;
import com.brianchiu.jwtdemo.dto.ProductQueryParams;
import com.brianchiu.jwtdemo.dto.ProductInsertRequest;
import com.brianchiu.jwtdemo.entity.Product;
import com.brianchiu.jwtdemo.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {

        String sql = "SELECT id, name, unit_price, stock, description, photo_url, brand, discount, type, shelf_date" +
                " FROM products WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //filtering
        sql = addFilteringSql(sql, map, productQueryParams);

        //sorting
        sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        //pagination
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        List<Product> list = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        System.out.println(sql);
        return list;
    }

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {

        String sql = "SELECT count(*) FROM products WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //filtering
        sql = addFilteringSql(sql, map, productQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        System.out.println(sql);
        return total;
    }

    @Override
    public Product getProductById(Integer productId) {

        String sql = "SELECT id, name, unit_price, stock, description, photo_url, brand, discount, type, shelf_date" +
                " FROM products WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        System.out.println(sql);
        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductInsertRequest productInsertRequest) {

        String sql = "INSERT INTO products(name, unit_price, stock, description, photo_url, brand, discount, type, shelf_date) VALUES" +
                " (:name, :unitPrice, :stock, :description, :photoUrl, :brand, :discount, :type, curdate())";

        Map<String, Object> map = new HashMap<>();
        map.put("name", productInsertRequest.getName());
        map.put("unitPrice", productInsertRequest.getUnitPrice());
        map.put("stock", productInsertRequest.getStock());
        map.put("description", productInsertRequest.getDescription());
        map.put("photoUrl", productInsertRequest.getPhotoUrl());
        map.put("brand", productInsertRequest.getBrand());
        map.put("discount", productInsertRequest.getDiscount());
        map.put("type", productInsertRequest.getType());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int productId = keyHolder.getKey().intValue();

        System.out.println(sql);
        return productId;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams){

        //filtering
        if (productQueryParams.getSearch() != null) {
            sql = sql + " AND name LIKE :search";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        return sql;
    }
}
