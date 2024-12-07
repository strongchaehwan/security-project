package com.example.security.users.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private int age;

    @Enumerated(EnumType.STRING)
    private Role roles;

    public User(String loginId, String password, int age, Role roles) {
        this.loginId = loginId;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }


}
