package com.mmamanagement.dto;

import jdk.jshell.Snippet;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.mmamanagement.entity.User}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    String name;
    String email;
    private String beltRank;
    private int sessionsToNextRank;
    int sessionsAttended;
    Set<Long> sessionIds; // Set of Session IDs the user is enrolled in

}