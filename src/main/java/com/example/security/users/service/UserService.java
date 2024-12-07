package com.example.security.users.service;

import com.example.security.users.domain.Role;
import com.example.security.users.domain.User;
import com.example.security.users.domain.dto.UserDto;
import com.example.security.users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void save(UserDto userDto){
        User user = new User(userDto.getLoginId(), userDto.getPassword(), userDto.getAge(), userDto.getRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @PostConstruct
    public void init(){
        UserDto user = new UserDto("test","1234",25, Role.USER);
        save(user);
    }
}
