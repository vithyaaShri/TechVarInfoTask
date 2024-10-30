package com.example.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
    private PayrollDto payrollDto;
}
