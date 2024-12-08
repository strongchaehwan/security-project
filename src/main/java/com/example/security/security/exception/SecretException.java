package com.example.security.security.exception;


import org.springframework.security.core.AuthenticationException;

public class SecretException extends AuthenticationException {
    public SecretException(String msg) {
        super(msg);
    }

    public SecretException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
