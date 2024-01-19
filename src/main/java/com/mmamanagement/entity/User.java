package com.mmamanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false)
    private String name;
//    @Column(nullable = false, unique = true)
    private String email;
//    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Session> sessions = new HashSet<>();

}