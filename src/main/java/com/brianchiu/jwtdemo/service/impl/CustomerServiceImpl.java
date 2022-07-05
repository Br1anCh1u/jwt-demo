package com.brianchiu.jwtdemo.service.impl;

import com.brianchiu.jwtdemo.dao.CustomerDao;
import com.brianchiu.jwtdemo.dto.CustomerForgetRequest;
import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.dto.CustomerUpdatePasswordRequest;
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

    @Override
    public Customer getCustomerByIdAndEmail(CustomerForgetRequest request) {
        return dao.getCustomerByIdAndEmail(request);
    }

    @Override
    public boolean insertCustomerForgetPassword(String id, String email, String uuid) {

        int count = dao.countCustomerForgetPasswordByIdAndEmail(id, email);

        if(count == 0){
            dao.insertCustomerForgetPassword(id, email, uuid);
            return true;
        } else if (count==1) {
            dao.updateCustomerForgetPassword(id, email, uuid);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Customer getCustomerByUUID(String uuid) {
        return dao.getCustomerByUUID(uuid);
    }

    @Override
    public Customer updatePasswordById(CustomerUpdatePasswordRequest request) {
        dao.updatePasswordById(request);
        return dao.getCustomerById(request.getId());
    }
}
