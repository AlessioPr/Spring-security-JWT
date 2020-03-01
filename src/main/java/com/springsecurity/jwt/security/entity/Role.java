package com.springsecurity.jwt.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, name = "name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
