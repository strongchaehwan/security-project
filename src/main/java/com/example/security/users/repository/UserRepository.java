package com.example.security.users.repository;

import com.example.security.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByLoginId(String loginId);
}
