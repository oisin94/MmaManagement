package com.mmamanagement.dto;

import com.mmamanagement.entity.Trainer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

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
    int maxCapacity;
    String description;
    LocalDateTime sessionTime;
    Trainer trainer;
    String beltRank;
    Set<Long> userIds;
}