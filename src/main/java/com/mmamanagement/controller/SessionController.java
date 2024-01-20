package com.mmamanagement.controller;


import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Session;
import com.mmamanagement.entity.Trainer;
import com.mmamanagement.service.SessionService;
import com.mmamanagement.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;

@Controller
public class SessionController {

    // field injection
    private SessionService sessionService;
    private TrainerService trainerService;


    // constructor injection
    public SessionController(SessionService sessionService, TrainerService trainerService) {
        this.sessionService = sessionService;
        this.trainerService = trainerService;
    }




    // create handler method, GET request and return model and view
    // http://localhost:8080/admin/sessions
    @GetMapping("/admin/sessions")
    public String sessions(Model model, @RequestParam(required = false) LocalDateTime startDate) {
        LocalDateTime startOfWeek = (startDate != null) ? startDate : LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = startOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));


        List<SessionDto> sessionsThisWeek = sessionService.findSessionsBetween(startOfWeek, endOfWeek);

        model.addAttribute("sessions1", sessionsThisWeek);
        model.addAttribute("startOfWeek", startOfWeek);
        model.addAttribute("endOfWeek", endOfWeek);
        return "admin/sessions";
    }


    @GetMapping("admin/session/newSession")
    public String newSessionForm(Model model){
        SessionDto sessionDto = new SessionDto();
        List<Trainer> trainers = trainerService.findAllTrainers();
        model.addAttribute("session1", sessionDto);
        model.addAttribute("trainers", trainers);
        return "admin/create_session";
    }

    // handler method to handle view post request
    @GetMapping("/admin/sessions/{sessionUrl}/view")
    public String viewSession(@PathVariable("sessionUrl") String sessionUrl,
                           Model model){
        SessionDto sessionDto = sessionService.findSessionByUrl(sessionUrl);
        model.addAttribute("session1", sessionDto);
        return "admin/view_session";

    }

    @GetMapping("/admin/sessions/{sessionId}/delete")
public String deleteSession(@PathVariable("sessionId") Long sessionId){
        sessionService.deleteSession(sessionId);
        return "redirect:/admin/sessions";
    }

    // handler method to handle edit post request
    // http://localhost:8080/admin/posts/1/edit
    @GetMapping("/admin/sessions/{sessionId}/edit")
    public String editSessionForm(@PathVariable("sessionId") Long sessionId,
                           Model model){
        SessionDto sessionDto = sessionService.findSessionById(sessionId);
        List<Trainer> trainers = trainerService.findAllTrainers();
        model.addAttribute("session1", sessionDto);
        model.addAttribute("trainers", trainers);
        return "admin/edit_session";
    }

    // handler method to handle edit post form submit request
    // http://localhost:8080/admin/posts/1
    @PostMapping("/admin/sessions/{sessionId}")
    public String updateSession(@PathVariable("sessionId") Long sessionId,
                             @Valid @ModelAttribute("session1") SessionDto sessionDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("session1", sessionDto);
            return "admin/edit_session";
        }
        sessionDto.setId(sessionId);
        sessionService.updateSession(sessionDto);
        return "redirect:/admin/sessions";
    }


    // handler method to handle form submit request
    // http://localhost:8080/admin/sessions
    @PostMapping("/admin/sessions")
    public String createSession(@Valid @ModelAttribute("session1") SessionDto sessionDto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("session1", sessionDto);
            return "admin/create_session";
        }
        sessionDto.setUrl(getUrl(sessionDto.getSessionName()));
        sessionService.createSession(sessionDto);
        return "redirect:/admin/sessions";
    }

    @GetMapping("/admin/sessions/search")
    public String searchSessions(@RequestParam(value ="query") String query,
                                 Model model){
        List<SessionDto> sessions = sessionService.searchSessions(query);
        model.addAttribute("sessions1", sessions);
        return "admin/sessions";
    }


    private static String getUrl(String sessionName){
        String baseTitle = sessionName.trim().toLowerCase().replaceAll("\\s+", "-")
                .replaceAll("[^A-Za-z0-9]", "-");
        String uniqueId = UUID.randomUUID().toString();
        return baseTitle + "-" + uniqueId;
    }




}
