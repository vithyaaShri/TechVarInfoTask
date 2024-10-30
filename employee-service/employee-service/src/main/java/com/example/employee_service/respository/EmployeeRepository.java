package com.example.employee_service.respository;

import com.example.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);

}
