package com.mmamanagement.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.mmamanagement.entity.Session}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    Long id;
    String name;
    String url;
    String description;
    LocalDateTime time;
    String trainer;
}