package com.example.security.security.config;

import com.example.security.security.service.CustomerUserDetailService;
import com.example.security.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/css/**", "/images/**", "/js/**", "favicon.*", "/*/icon-*").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "admin") // ADMIN 또는 admin
                        .requestMatchers("/user/**").hasAnyRole("USER", "user") // MEMBER 또는 member
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER", "manager") // GOOGLE 또는 google
                        .requestMatchers("/", "/signup", "/login").permitAll()
                        .anyRequest().authenticated()
        );

        httpSecurity.formLogin(formLogin ->
                formLogin.loginPage("/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")

        );

        httpSecurity.userDetailsService(new CustomerUserDetailService(userRepository));


        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
