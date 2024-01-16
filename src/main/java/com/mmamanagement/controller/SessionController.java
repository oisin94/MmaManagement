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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

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
    public String newPostForm(Model model){
        SessionDto sessionDto = new SessionDto();
        List<Trainer> trainers = trainerService.findAllTrainers();
        model.addAttribute("session1", sessionDto);
        model.addAttribute("trainers", trainers);
        return "admin/create_session";
    }



//    // handler method to handle form submit request
//    @PostMapping("/admin/posts")
//    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
//                             BindingResult result,
//                             Model model){
//        if(result.hasErrors()){
//            model.addAttribute("post", postDto);
//            return "admin/create_post";
//        }
//        postDto.setUrl(getUrl(postDto.getTitle()));
//        postService.createPost(postDto);
//        return "redirect:/admin/posts";
//    }

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

    private static String getUrl(String sessionName){
        // OOPS Concepts Explained in Java
        // oops-concepts-explained-in-java
        String title = sessionName.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }



}