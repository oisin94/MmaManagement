package com.mmamanagement.controller;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class UserController {

    private SessionService sessionService;

    public UserController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/user/sessions")
    public String viewUserSchedule(Model model, @RequestParam(required = false) LocalDateTime startDate) {
        LocalDateTime startOfWeek = (startDate != null) ? startDate : LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = startOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<SessionDto> sessionsThisWeek = sessionService.findSessionsBetween(startOfWeek, endOfWeek);

        model.addAttribute("sessionResponse", sessionsThisWeek);
        model.addAttribute("startOfWeek", startOfWeek);
        model.addAttribute("endOfWeek", endOfWeek);
        return "user/view_sessions";
    }

    @GetMapping("/user/session/{sessionUrl}")
    public String viewSession(@PathVariable("sessionUrl") String sessionUrl,
                               Model model){
        SessionDto session = sessionService.findSessionByUrl(sessionUrl);
        model.addAttribute("session1", session);
        return "user/book_session";
    }


    @PostMapping("/user/session/{sessionId}/book")
    public String bookSession(@PathVariable Long sessionId, Principal principal) {
        // The Principal object can be used to get the currently logged-in user's details
        // Implement the logic to book the session for the user
        // Redirect to a confirmation page or back to the session list
        return "redirect:/user/sessions";
    }

}
