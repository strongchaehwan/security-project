package com.example.security.users.domain.dto;

import com.example.security.users.domain.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String loginId;

    private String password;

    private int age;

    private Role roles;
}
