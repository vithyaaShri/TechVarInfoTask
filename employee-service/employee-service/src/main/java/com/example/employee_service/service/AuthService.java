package com.example.employee_service.service;

import com.example.employee_service.dto.JwtAuthResponse;
import com.example.employee_service.dto.LoginDto;
import com.example.employee_service.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto);
}
