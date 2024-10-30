package com.example.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  PasswordRequestUtil{
    private  String username;
    private String oldPassword;
    private String newPassword;
}
