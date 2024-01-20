package com.mmamanagement.dto;

import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.mmamanagement.entity.Attendance}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long sessionId;

    private LocalDateTime attendanceDate;
}