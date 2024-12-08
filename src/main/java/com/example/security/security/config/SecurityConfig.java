package com.example.security.security.config;

import com.example.security.security.handler.CustomAuthenticationFailureHandler;
import com.example.security.security.handler.CustomAuthenticationSuccessHandler;
import com.example.security.security.service.CustomerUserDetailService;
import com.example.security.users.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
   // private final AuthenticationProvider authenticationProvider;
    private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,AuthenticationProvider authenticationProvider) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/css/**", "/images/**", "/js/**", "favicon.*", "/*/icon-*").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "admin") // ADMIN 또는 admin
                        .requestMatchers("/user/**").hasAnyRole("USER", "user") // MEMBER 또는 member
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER", "manager") // GOOGLE 또는 google
                        .requestMatchers("/", "/signup", "/login*").permitAll()
                        .anyRequest().authenticated()
        );

        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.formLogin(formLogin ->
                formLogin.loginPage("/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .authenticationDetailsSource(authenticationDetailsSource)
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .failureHandler(new CustomAuthenticationFailureHandler())

        );

        httpSecurity.logout(logout ->
                logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
        );


        //httpSecurity.userDetailsService(new CustomerUserDetailService(userRepository));


        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
