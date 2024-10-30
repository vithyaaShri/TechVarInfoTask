package com.example.employee_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private int employeeId;
    @NotNull(message = "FirstName is mandatory")
    private String firstName;
    private String lastName;
    @Email
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotNull(message = "Phone Number should not be empty")
    @Pattern(regexp = "^\\d{10}$")
    @Size(min=10,max=10)
    private String phoneNo;
    private String address;
    private String departmentCode;
    private Long payRollId;
}
