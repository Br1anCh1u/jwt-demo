package com.brianchiu.jwtdemo.dao.impl;

import com.brianchiu.jwtdemo.dao.CustomerDao;
import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.entity.Customer;
import com.brianchiu.jwtdemo.rowmapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Customer getCustomerByEmailAndPassword(CustomerLoginRequest request) {

        String sql = "SELECT id, phone, email, password, name, gender, birthday, address, subscribed, discount, role FROM customers WHERE email = :email and password = :password";

        Map<String, Object> map = new HashMap<>();
        map.put("email", request.getEmail());
        map.put("password", request.getPassword());

        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<Customer> getAllCustomer() {

        String sql = "SELECT id, phone, email, password, name, gender, birthday, address, subscribed, discount, role FROM customers WHERE 1=1";

        List<Customer> list = namedParameterJdbcTemplate.query(sql, new HashMap<>(), new CustomerRowMapper());

        return list;
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        String sql = "SELECT id, phone, email, password, name, gender, birthday, address, subscribed, discount, role FROM customers WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void insertCustomer(CustomerRegisterRequest request) {

        String sql = "INSERT INTO customers (id, phone, email, password, name, gender, birthday, role) " +
                "VALUES (:id, :phone, :email, :password, 'name', 'M', '1993-07-23', '1') ";

        Map<String, Object> map = new HashMap<>();
        map.put("id", request.getId());
        map.put("phone", request.getPhone());
        map.put("email", request.getEmail());
        map.put("password", request.getPassword());

        namedParameterJdbcTemplate.update(sql, map);

    }
}
