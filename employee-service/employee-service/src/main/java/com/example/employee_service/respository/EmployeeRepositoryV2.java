package com.example.employee_service.respository;

import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.EmployeeV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryV2 extends JpaRepository<EmployeeV2, Integer> {
    EmployeeV2 findByEmail(String email);
}
