package com.mmamanagement.service;

import com.mmamanagement.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {
    List<AttendanceDto> getAllAttendances();

    AttendanceDto getAttendanceById(Long id);

    void createAttendance(AttendanceDto attendanceDto);
}
