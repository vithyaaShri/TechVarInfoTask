package com.example.employee_service.service;

import com.example.employee_service.dto.ApiResponseDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.dto.EmployeeDtoV2;
import com.example.employee_service.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDtoV2 addEmployeeV2(EmployeeDtoV2 employee);
    ApiResponseDto getEmployeeById(Long id);
    List<ApiResponseDto> getEmployees();
    EmployeeDto addEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(Long id,EmployeeDto employee);
    EmployeeDto getEmployeeByEmail(String email);
    void deleteEmployee(Long id);

}
