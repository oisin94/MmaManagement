package com.mmamanagement.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.mmamanagement.entity.User}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    String name;
    String email;
    String password;
}