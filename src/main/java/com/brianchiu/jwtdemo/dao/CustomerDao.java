package com.brianchiu.jwtdemo.dao;

import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getCustomerByEmailAndPassword(CustomerLoginRequest request);

    List<Customer> getAllCustomer();

    Customer getCustomerByEmail(String email);

    void insertCustomer(CustomerRegisterRequest request);
}
