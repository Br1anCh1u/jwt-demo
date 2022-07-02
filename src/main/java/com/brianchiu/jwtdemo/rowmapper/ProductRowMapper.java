package com.brianchiu.jwtdemo.rowmapper;

import com.brianchiu.jwtdemo.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setUnitPrice(rs.getDouble("unit_price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setPhotoUrl(rs.getString("photo_url"));
        product.setBrand(rs.getString("brand"));
        product.setDiscount(rs.getInt("discount"));
        product.setType(rs.getString("type"));
        product.setShelfDate(rs.getDate("shelf_date"));

        return product;
    }
}
