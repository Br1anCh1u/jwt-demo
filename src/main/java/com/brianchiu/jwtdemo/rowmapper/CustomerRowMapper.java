package com.brianchiu.jwtdemo.rowmapper;

import com.brianchiu.jwtdemo.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {


    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();
        customer.setId(rs.getString("id"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setName(rs.getString("name"));
        customer.setGender(rs.getString("gender").charAt(0));
        customer.setBirthday(rs.getString("birthday"));
        customer.setAddress(rs.getString("address"));
        customer.setSubscribed(rs.getBoolean("subscribed"));
        customer.setDiscount(rs.getBoolean("discount"));
        customer.setRoleId(rs.getInt("role"));

        return customer;
    }
}
