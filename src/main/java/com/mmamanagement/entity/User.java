package com.mmamanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Builder
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

    private String beltRank;

    private int sessionsAttended;

    private int sessionsToNextRank;

    @ManyToMany(mappedBy = "users")
    private Set<Session> sessions = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Attendance> attendances = new HashSet<>();

}