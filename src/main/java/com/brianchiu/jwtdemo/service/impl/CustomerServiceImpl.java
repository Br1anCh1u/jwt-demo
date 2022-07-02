package com.brianchiu.jwtdemo.service.impl;

import com.brianchiu.jwtdemo.dao.CustomerDao;
import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.entity.Customer;
import com.brianchiu.jwtdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public Customer login(CustomerLoginRequest request) {

        Customer customer = dao.getCustomerByEmailAndPassword(request);

        if(customer==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return dao.getAllCustomer();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer customer = dao.getCustomerByEmail(email);

        if(customer==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return customer;
    }

    @Override
    public void register(CustomerRegisterRequest request) {

        if(dao.getCustomerById(request.getId())!=null || dao.getCustomerByEmail(request.getEmail())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        dao.insertCustomer(request);
    }
}
