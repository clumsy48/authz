package com.github.clumsy48.auth.dtos;

import lombok.Getter;

@Getter
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

}