package com.mmamanagement.controller;

import com.mmamanagement.dto.AttendanceDto;
import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Trainer;
import com.mmamanagement.service.AttendanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendances")
    public String listAttendances(Model model) {
        model.addAttribute("attendances", attendanceService.getAllAttendances());
        return "attendance/list";
    }

    @GetMapping("/attendance/{id}")
    public String getAttendance(@PathVariable Long id, Model model) {
        model.addAttribute("attendance", attendanceService.getAttendanceById(id));
        return "attendance/detail";
    }

    @GetMapping("admin/attendance/newAttendance")
    public String newAttendanceForm(@RequestParam("sessionId") Long sessionId,
                                    @RequestParam("sessionDate") LocalDateTime sessionDate,
                                    Model model) {
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setSessionId(sessionId);
        attendanceDto.setAttendanceDate(sessionDate);
        model.addAttribute("attendance", attendanceDto);
        return "admin/take_attendance";
    }

    @PostMapping("/admin/attendance")
    public String createAttendance(@ModelAttribute AttendanceDto attendanceDto) {
        attendanceService.createAttendance(attendanceDto);
        return "redirect:/admin/sessions";
    }


}
