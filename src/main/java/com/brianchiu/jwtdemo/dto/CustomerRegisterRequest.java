package com.brianchiu.jwtdemo.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRegisterRequest {

    private String id;
    private String phone;
    private String email;
    private String password;

}
