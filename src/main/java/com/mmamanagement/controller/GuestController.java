package com.mmamanagement.controller;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class GuestController {

    private final SessionService sessionService;

    public GuestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public String viewSchedule(Model model, @RequestParam(required = false) LocalDateTime startDate) {
        LocalDateTime startOfWeek = (startDate != null) ? startDate : LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = startOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<SessionDto> sessionsThisWeek = sessionService.findSessionsBetween(startOfWeek, endOfWeek);

        model.addAttribute("sessionResponse", sessionsThisWeek);
        model.addAttribute("startOfWeek", startOfWeek);
        model.addAttribute("endOfWeek", endOfWeek);
        return "guest/view_schedule";
    }

}
