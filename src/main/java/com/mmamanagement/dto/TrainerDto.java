package com.mmamanagement.dto;

import com.mmamanagement.entity.Session;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.mmamanagement.entity.Trainer}
 */
@Value
public class TrainerDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Session session;
}