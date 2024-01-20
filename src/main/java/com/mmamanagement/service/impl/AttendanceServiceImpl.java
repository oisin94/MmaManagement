package com.mmamanagement.service.impl;

import com.mmamanagement.dto.AttendanceDto;
import com.mmamanagement.entity.Attendance;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.User;
import com.mmamanagement.mapper.AttendanceMapper;
import com.mmamanagement.repository.AttendanceRepository;
import com.mmamanagement.repository.SessionRepository;
import com.mmamanagement.repository.UserRepository;
import com.mmamanagement.service.AttendanceService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 SessionRepository sessionRepository,
                                 UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AttendanceDto> getAllAttendances() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendances.stream()
                .map(AttendanceMapper::mapToAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        return AttendanceMapper.mapToAttendanceDto(attendance);
    }

    @Override
    public void createAttendance(AttendanceDto attendanceDto) {
        Session session = sessionRepository.findById(attendanceDto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));
        User user = userRepository.findById(attendanceDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Attendance attendance = AttendanceMapper.mapToAttendance(attendanceDto, session, user);
        attendanceRepository.save(attendance);
        updateProgressToNextRank(user);
    }

    private void updateProgressToNextRank(User user) {
        Long userId = user.getId();
        int attendanceCount = attendanceRepository.countByUserId(userId).intValue();
        user.setSessionsAttended(attendanceCount);

        user.setSessionsToNextRank(calculateSessionsToNextRank(attendanceCount, user.getBeltRank()));
        updateBeltRankIfNeeded(user);
        userRepository.save(user);
    }

    private int calculateSessionsToNextRank(int attendanceCount, String beltRank) {
        int sessionsToNextRank = 0;
        switch (beltRank) {
            case "White":
                sessionsToNextRank = Math.max(0, 10 - attendanceCount);
                break;
            case "Blue":
                sessionsToNextRank = Math.max(0, 20 - attendanceCount);
                break;
            case "Purple":
                sessionsToNextRank = Math.max(0, 30 - attendanceCount);
                break;
            case "Brown":
                sessionsToNextRank = Math.max(0, 40 - attendanceCount);
                break;
            case "Black":
                // Assuming there is no next rank after Black
                sessionsToNextRank = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid belt rank: " + beltRank);
        }
        return sessionsToNextRank;
    }

    private void updateBeltRankIfNeeded(User user) {
        if (user.getSessionsToNextRank() <= 0) {
            String newBeltRank = calculateNextBeltRank(user.getBeltRank());
            user.setBeltRank(newBeltRank);
            // Reset sessions to next rank for the new belt rank
            user.setSessionsToNextRank(calculateSessionsToNextRank(0, newBeltRank));
        }
    }

    private String calculateNextBeltRank(String currentBeltRank) {
        switch (currentBeltRank) {
            case "White":
                return "Blue";
            case "Blue":
                return "Purple";
            case "Purple":
                return "Brown";
            case "Brown":
                return "Black";
            case "Black":
                return "Black";
            default:
                throw new IllegalArgumentException("Invalid current belt rank: " + currentBeltRank);
        }
    }
}