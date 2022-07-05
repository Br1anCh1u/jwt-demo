package com.brianchiu.jwtdemo.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerUpdatePasswordRequest {

    private String id;
    private String password;

}
