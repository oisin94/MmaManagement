package com.mmamanagement.dto;

import com.mmamanagement.entity.Trainer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.mmamanagement.entity.Session}
 */
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    Long id;
    String sessionName;
    String url;
    String description;
    LocalDateTime sessionTime;
    Trainer trainer;
    String beltRank;
}