package com.brianchiu.jwtdemo.dao.impl;

import com.brianchiu.jwtdemo.dao.CustomerDao;
import com.brianchiu.jwtdemo.dto.CustomerForgetRequest;
import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.dto.CustomerUpdatePasswordRequest;
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

        System.out.println(sql);
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

        System.out.println(sql);
        return list;
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        String sql = "SELECT id, phone, email, password, name, gender, birthday, address, subscribed, discount, role FROM customers WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());

        System.out.println(sql);
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
        System.out.println(sql);

    }

    @Override
    public Customer getCustomerById(String id) {

        String sql = "SELECT id, phone, email, password, name, gender, birthday, address, subscribed, discount, role FROM customers WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());

        System.out.println(sql);
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Customer getCustomerByIdAndEmail(CustomerForgetRequest request) {

        String sql = "SELECT id, phone, email, password, name, gender, birthday, address, subscribed, discount, role FROM customers WHERE id = :id AND email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("id", request.getId());
        map.put("email", request.getEmail());

        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());

        System.out.println(sql);
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void insertCustomerForgetPassword(String id, String email, String uuid) {

        String sql = "INSERT INTO customer_forget_password (customer_id, customer_email, uuid)" +
                " VALUES (:customerId, :customerEmail, :uuid)";

        Map<String, Object> map = new HashMap<>();
        map.put("customerId", id);
        map.put("customerEmail", email);
        map.put("uuid", uuid);

        namedParameterJdbcTemplate.update(sql, map);
        System.out.println(sql);
    }

    @Override
    public Customer getCustomerByUUID(String uuid) {

        String sql = "SELECT * FROM customers " +
                "LEFT JOIN customer_forget_password " +
                "ON customer_forget_password.customer_Id = customers.id " +
                "AND customer_forget_password.customer_email = customers.email " +
                "WHERE uuid = :uuid";

        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);

        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());

        System.out.println(sql);
        if(list.size()>0){
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updatePasswordById(CustomerUpdatePasswordRequest request) {

        String sql = "UPDATE customers SET `password` = :password WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("password", request.getPassword());
        map.put("id", request.getId());

        namedParameterJdbcTemplate.update(sql, map);
        System.out.println(sql);
    }

    @Override
    public int countCustomerForgetPasswordByIdAndEmail(String id, String email) {

        String sql = "SELECT COUNT(*) FROM customer_forget_password WHERE customer_id = :id AND customer_email = :email";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("email", email);

        Integer count = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        System.out.println(sql);
        if(count == null){
            return 0;
        } else {
            return count;
        }

    }

    @Override
    public void updateCustomerForgetPassword(String id, String email, String uuid) {
        String sql = "UPDATE customer_forget_password SET uuid = :uuid WHERE customer_id = :id AND customer_email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("id", id);
        map.put("email", email);

        namedParameterJdbcTemplate.update(sql, map);
        System.out.println(sql);

    }
}
