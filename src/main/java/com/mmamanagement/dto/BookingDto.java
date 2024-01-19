package com.mmamanagement.dto;

import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.mmamanagement.entity.Booking}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BookingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


}