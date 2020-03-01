package com.springsecurity.jwt.security.service.impl;

import com.springsecurity.jwt.security.entity.User;
import com.springsecurity.jwt.security.jwt.JwtService;
import com.springsecurity.jwt.security.service.AuthoritySevice;
import com.springsecurity.jwt.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthoritySevice {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public String generateToke(String userName) {
        User user = userService.findByName(userName);
        return jwtService.generateToken(user.getUserName(), user.getRoles());
    }
}
