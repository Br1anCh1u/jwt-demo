package com.brianchiu.jwtdemo.dao;

import com.brianchiu.jwtdemo.dto.CustomerForgetRequest;
import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.dto.CustomerUpdatePasswordRequest;
import com.brianchiu.jwtdemo.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getCustomerByEmailAndPassword(CustomerLoginRequest request);

    List<Customer> getAllCustomer();

    Customer getCustomerByEmail(String email);

    void insertCustomer(CustomerRegisterRequest request);

    Customer getCustomerById(String id);

    Customer getCustomerByIdAndEmail(CustomerForgetRequest request);

    void insertCustomerForgetPassword(String id, String email, String uuid);

    Customer getCustomerByUUID(String uuid);

    void updatePasswordById(CustomerUpdatePasswordRequest request);

    int countCustomerForgetPasswordByIdAndEmail(String id, String email);

    void updateCustomerForgetPassword(String id, String email, String uuid);
}
