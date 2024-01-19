package com.mmamanagement.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

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
    Set<Long> sessionIds; // Set of Session IDs the user is enrolled in

}