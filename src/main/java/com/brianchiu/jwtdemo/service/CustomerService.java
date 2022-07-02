package com.brianchiu.jwtdemo.service;

import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer login(CustomerLoginRequest request);

    List<Customer> getAllCustomer();

    Customer getCustomerByEmail(String email);

    void register(CustomerRegisterRequest request);
}
