package com.springsecurity.jwt.security.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
