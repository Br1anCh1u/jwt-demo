package com.brianchiu.jwtdemo.controller;

import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.security.JwtResponse;
import com.brianchiu.jwtdemo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public JwtResponse createJwtToken(@RequestBody CustomerLoginRequest request) throws Exception {
        return jwtService.createJwtToken(request);
    }

}
