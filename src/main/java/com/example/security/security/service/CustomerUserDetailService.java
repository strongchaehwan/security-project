package com.example.security.security.service;

import com.example.security.users.domain.User;
import com.example.security.users.domain.dto.AccountContext;
import com.example.security.users.domain.dto.UserDto;
import com.example.security.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomerUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username" + loginId);
        }

        UserDto userDto = new UserDto(user.getLoginId(), user.getPassword(), user.getAge(), user.getRoles());
        return new AccountContext(userDto);

    }
}
