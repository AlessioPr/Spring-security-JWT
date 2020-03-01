package com.springsecurity.jwt.security.repository;

import com.springsecurity.jwt.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    void deleteByEmail(String email);
    User findByEmail(String email);
    List<User> findAll();
}
