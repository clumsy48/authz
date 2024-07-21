package com.github.clumsy48.auth.dtos;

import com.github.clumsy48.auth.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class SignUpResponse {

    private  User user;

    private final HttpStatus status;

    private String error;

    private String message;
}
