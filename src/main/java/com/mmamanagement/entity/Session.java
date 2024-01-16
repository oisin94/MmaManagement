package com.mmamanagement.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionName;

    private String url;

    private String description;

    private LocalDateTime sessionTime;

    private String beltRank;


    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "session")
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "session")
    private Set<Attendee> attendees = new HashSet<>();


}