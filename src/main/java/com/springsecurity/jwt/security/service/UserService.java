package com.springsecurity.jwt.security.service;

import com.springsecurity.jwt.security.dto.UserRegistrationDto;
import com.springsecurity.jwt.security.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(UserRegistrationDto user);
    void deleteUser(String email);
    List<User> getAll();
    User findByName(String userName);
    User findByEmail(String email);
}
