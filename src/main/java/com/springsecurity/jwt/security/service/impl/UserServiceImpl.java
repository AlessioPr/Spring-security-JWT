package com.springsecurity.jwt.security.service.impl;

import com.springsecurity.jwt.security.dto.UserRegistrationDto;
import com.springsecurity.jwt.security.entity.User;
import com.springsecurity.jwt.security.repository.UserRepository;
import com.springsecurity.jwt.security.service.UserService;
import com.springsecurity.jwt.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User saveUser(UserRegistrationDto userRegistrationDto) {
        userRepository.save(UserConverter.toUserEntity(userRegistrationDto));
        userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        deleteUser(userRegistrationDto.getEmail());
        return userRepository.save(UserConverter.toUserEntity(userRegistrationDto));
    }

    @Override
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
