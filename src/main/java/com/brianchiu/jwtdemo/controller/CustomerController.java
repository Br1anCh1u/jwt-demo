package com.brianchiu.jwtdemo.controller;

import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.entity.Customer;
import com.brianchiu.jwtdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomer(){
        List<Customer> list = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/customer/register")
    public ResponseEntity<Customer> register(@RequestBody CustomerRegisterRequest request){

        customerService.register(request);

        Customer customer = customerService.getCustomerByEmail(request.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

}
