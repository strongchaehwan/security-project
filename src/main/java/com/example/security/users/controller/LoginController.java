package com.example.security.users.controller;


import com.example.security.users.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("roles", Role.values()); // Enum 값을 모델에 추가
        return "login/signup"; // signup.html로 이동
    }
}
