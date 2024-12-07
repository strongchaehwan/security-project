package com.example.security.users.controller;


import com.example.security.users.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
//
//        //인증된 사용자가 로그인한 상태 즉 널이아니면
//        if(authentication != null){
//            new SecurityContextLogoutHandler().logout(request,response,authentication);
//        }
//
//        return "redirect:/";
//    }

}
