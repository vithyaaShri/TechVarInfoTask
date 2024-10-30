package com.example.employee_service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private  String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private  String email;
}
