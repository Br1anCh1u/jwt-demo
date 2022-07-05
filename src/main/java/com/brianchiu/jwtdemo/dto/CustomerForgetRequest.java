package com.brianchiu.jwtdemo.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerForgetRequest {

    private String id;
    private String email;
}
