package com.springsecurity.jwt.utils;

import com.springsecurity.jwt.dto.RoleDto;
import com.springsecurity.jwt.security.dto.UserRegistrationDto;
import com.springsecurity.jwt.security.entity.Role;
import com.springsecurity.jwt.security.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    public static User toUserEntity(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setUserName(userRegistrationDto.getName());
        user.setPassword(userRegistrationDto.getPassword());
        user.setEmail(userRegistrationDto.getEmail());
        Role role = new Role();
        role.setRoleName(userRegistrationDto.getRole());
        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        return user;
    }

    public static List<RoleDto> toRoleDtoList (List<Role> roles) {
        return roles.stream()
                .map(role -> new RoleDto(role.getRoleName()))
                .collect(Collectors.toList());
    }



    public static List<SimpleGrantedAuthority> getUserRolesFromStringList(List<String> list) {
        return list.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static List<SimpleGrantedAuthority> getUserRoles(List<Role> list) {
        return list.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

}
