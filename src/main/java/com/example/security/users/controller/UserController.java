package com.example.security.users.controller;


import com.example.security.users.domain.dto.UserDto;
import com.example.security.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDto userDto){
        userService.save(userDto);
        return "redirect:/";
    }
}
