package com.mmamanagement.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.mmamanagement.entity.Attendee}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeDto implements Serializable {
    Long id;
    Long userId;
}