package com.brianchiu.jwtdemo.controller;

import com.brianchiu.jwtdemo.dto.CustomerForgetRequest;
import com.brianchiu.jwtdemo.dto.CustomerRegisterRequest;
import com.brianchiu.jwtdemo.dto.CustomerUpdatePasswordRequest;
import com.brianchiu.jwtdemo.entity.Customer;
import com.brianchiu.jwtdemo.entity.EmailDetail;
import com.brianchiu.jwtdemo.service.CustomerService;
import com.brianchiu.jwtdemo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

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

    //按忘記密碼
    @PostMapping("/customer/forgetPassword")
    public ResponseEntity<HttpStatus> forgetPassword(@RequestBody CustomerForgetRequest request){

        Customer customer = customerService.getCustomerByIdAndEmail(request);

        if(customer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        UUID uuid = UUID.randomUUID();

        boolean isInserted = customerService.insertCustomerForgetPassword(customer.getId(), customer.getEmail(), uuid.toString());

        if(isInserted){
            EmailDetail detail = new EmailDetail();
            detail.setRecipient(customer.getEmail());
            detail.setSubject("[SpringBoot Email Demo] Forget Password");
            detail.setMsgBody(uuid.toString());

            System.out.println(emailService.sendSimpleMail(detail));
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //將email將到的uuid傳過來
    @PostMapping("/customer/forgetPasswordCheckUUID")
    public ResponseEntity<Customer> checkUUID(@RequestBody Map<String,String> map){

        String uuid = map.get("uuid");

        Customer customer = customerService.getCustomerByUUID(uuid);

        if(customer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }
    }

    //改密碼
    @PostMapping("/customer/updatePassword")
    public ResponseEntity<Customer> updatePassword(@RequestBody CustomerUpdatePasswordRequest request){

        Customer customer = customerService.updatePasswordById(request);

        if(customer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }

    }

}
