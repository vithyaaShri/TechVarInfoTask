package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="department-service",url="http://localhost:8080")
public interface ApiClient {
    @GetMapping("/api/department/getByCode/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable String code) ;
}
