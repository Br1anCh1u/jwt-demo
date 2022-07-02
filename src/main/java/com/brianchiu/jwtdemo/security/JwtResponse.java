package com.brianchiu.jwtdemo.security;

import com.brianchiu.jwtdemo.entity.Customer;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class JwtResponse {

    private Customer customer;
    private String jwtToken;

}
