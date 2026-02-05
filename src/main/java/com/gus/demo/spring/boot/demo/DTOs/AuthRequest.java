package com.gus.demo.spring.boot.demo.DTOs;

import jakarta.servlet.Filter;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
