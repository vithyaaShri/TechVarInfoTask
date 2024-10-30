package com.example.employee_service.service;

import com.example.employee_service.dto.PayrollDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="payroll-service",url="http://localhost:8082")
public interface ApiClientPayroll {

    @GetMapping("/api/payroll/getPayroll/{id}")
   PayrollDto getPayroll(@PathVariable Long id);
}
