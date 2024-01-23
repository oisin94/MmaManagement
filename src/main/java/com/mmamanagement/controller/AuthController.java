package com.mmamanagement.controller;


import jakarta.validation.Valid;
import com.mmamanagement.dto.RegistrationDto;
import com.mmamanagement.entity.User;
import com.mmamanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Handle method to manage user registration request
    // http://localhost:8080/signup
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        // this object holds form data
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle login page request
    // http://localhost:8080/login
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // user registration form submit request
    // http://localhost:8080/register
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/register?success";
    }

}
