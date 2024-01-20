package com.mmamanagement.repository;

import com.mmamanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    public Long countByUserId(Long userId);
}