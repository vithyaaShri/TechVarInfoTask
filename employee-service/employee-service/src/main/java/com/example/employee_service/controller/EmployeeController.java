package com.example.employee_service.controller;

import com.example.employee_service.dto.ApiResponseDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.dto.EmployeeDtoV2;
import com.example.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Endpoint to get a Employee Detail Based on the EmployeeId
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable Long id) {
        ApiResponseDto apiResponseDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(apiResponseDto);

    }

    //Endpoint to get all Employees detail in a list and Display it
    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<ApiResponseDto>> getAllEmployee() {
        List<ApiResponseDto> employeeDtos=employeeService.getEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    //createEmployee for version 1
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/save")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee=employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    //createEmployee for version 2
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v2/save")
    public ResponseEntity<EmployeeDtoV2> createEmployeeV2(@Valid @RequestBody EmployeeDtoV2 employeeDto) {
        EmployeeDtoV2 createdEmployee=employeeService.addEmployeeV2(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    //Endpoint to call for updating Employee Details
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee=employeeService.updateEmployee(id,employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.ACCEPTED);
    }

    //EndPoint to delete an Employee Detail database
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }

    //End Point to get Employee based on Email Field
    @GetMapping("/getByEmail/{Email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String Email) {
        EmployeeDto employeeDto=employeeService.getEmployeeByEmail(Email);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

}
