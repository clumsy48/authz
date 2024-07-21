package com.github.clumsy48.auth.controller;

import com.github.clumsy48.auth.dtos.LoginResponse;
import com.github.clumsy48.auth.dtos.LoginUserDto;
import com.github.clumsy48.auth.dtos.RegisterUserDto;
import com.github.clumsy48.auth.dtos.SignUpResponse;
import com.github.clumsy48.auth.entity.User;
import com.github.clumsy48.auth.service.AuthenticationService;
import com.github.clumsy48.auth.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RequestMapping("/auth")
@RestController
@Slf4j
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            User registeredUser = authenticationService.signup(registerUserDto);
            return ResponseEntity.ok(SignUpResponse.builder().user(registeredUser).message("user created").status(HttpStatus.CREATED).build());
        } catch (Exception ex) {
            log.error("",ex);
            return new ResponseEntity<>(SignUpResponse.builder().status(HttpStatus.BAD_REQUEST).error(ex.getMessage()).build(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
        } catch (Exception ex) {
            log.error("",ex);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}