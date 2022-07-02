package com.brianchiu.jwtdemo.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerLoginRequest {

    private String email;
    private String password;

}
