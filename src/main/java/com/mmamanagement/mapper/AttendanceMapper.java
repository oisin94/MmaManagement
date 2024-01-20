package com.mmamanagement.mapper;


import com.mmamanagement.entity.Attendance;
import com.mmamanagement.dto.AttendanceDto;
import com.mmamanagement.entity.User;
import com.mmamanagement.entity.Session;

public class AttendanceMapper {
    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.getId())
                .sessionId(attendance.getSession().getId())
                .userId(attendance.getUser().getId())
                .attendanceDate(attendance.getAttendanceDate())
                .build();
    }

    public static Attendance mapToAttendance(AttendanceDto attendanceDto, Session session, User user) {
        Attendance attendance = new Attendance();
        attendance.setId(attendanceDto.getId());
        attendance.setSession(session);
        attendance.setUser(user);
        attendance.setAttendanceDate(attendanceDto.getAttendanceDate());
        return attendance;
    }


}
