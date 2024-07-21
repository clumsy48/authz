package com.github.clumsy48.auth.dtos;

import lombok.Getter;

@Getter
public class LoginUserDto {
    private String email;
    
    private String password;

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}