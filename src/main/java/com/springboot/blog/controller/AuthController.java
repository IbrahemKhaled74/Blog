package com.springboot.blog.controller;

import com.springboot.blog.dto.JWTResponse;
import com.springboot.blog.dto.LoginDto;
import com.springboot.blog.dto.RegisterDto;
import com.springboot.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JWTResponse>login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWTResponse jwtResponse= new JWTResponse();
        jwtResponse.setToken(token);
        return ResponseEntity.ok(jwtResponse);
    }
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String>register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }
}
