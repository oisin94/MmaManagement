package com.mmamanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "trainer")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    private String lastName;

    private String email;

    private String password;

    // private string description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "trainer")
    private Set<Session> session;



}