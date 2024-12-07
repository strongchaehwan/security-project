package com.example.security.users.domain.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AcoountContext implements UserDetails {

    private final UserDto userDto;

    public AcoountContext(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_" + userDto.getRoles();
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userDto.getLoginId();
    }
}

