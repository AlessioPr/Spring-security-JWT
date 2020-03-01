package com.springsecurity.jwt.security.dto;

import lombok.Data;

@Data
public class UserLoginDto {

    private String name;
    private String password;
}
