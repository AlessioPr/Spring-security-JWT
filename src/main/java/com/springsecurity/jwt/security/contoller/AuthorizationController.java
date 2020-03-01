package com.springsecurity.jwt.security.contoller;

import com.springsecurity.jwt.security.dto.UserLoginDto;
import com.springsecurity.jwt.security.entity.User;
import com.springsecurity.jwt.security.jwt.JwtService;
import com.springsecurity.jwt.security.service.AuthoritySevice;
import com.springsecurity.jwt.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/login")
public class AuthorizationController {

    private final AuthoritySevice authoritySevice;



    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginDto loginDto) {

        return ResponseEntity.ok(authoritySevice.generateToke(loginDto.getName()));
    }
}
