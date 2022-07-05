package com.brianchiu.jwtdemo.service;

import com.brianchiu.jwtdemo.dto.CustomerForgetRequest;
import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.dto.CustomerUpdatePasswordRequest;
import com.brianchiu.jwtdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer login(CustomerLoginRequest request);

    List<Customer> getAllCustomer();

    Customer getCustomerByEmail(String email);

    void register(CustomerRegisterRequest request);

    Customer getCustomerByIdAndEmail(CustomerForgetRequest request);

    boolean insertCustomerForgetPassword(String id, String email, String toString);

    Customer getCustomerByUUID(String uuid);

    Customer updatePasswordById(CustomerUpdatePasswordRequest request);
}
