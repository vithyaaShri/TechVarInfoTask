package com.example.employee_service.controller;

import com.example.employee_service.dto.JwtAuthResponse;
import com.example.employee_service.dto.LoginDto;
import com.example.employee_service.dto.RegisterDto;
import com.example.employee_service.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    AuthService authService;
    //End Points to register User
    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto){
        String registermessage=authService.register(registerDto);
        return registermessage;
    }
    //Login Controller to get Login detail and Process it
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        JwtAuthResponse jwtAuthResponse =authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
