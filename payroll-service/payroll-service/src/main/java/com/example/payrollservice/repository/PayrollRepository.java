package com.example.payrollservice.repository;

import com.example.payrollservice.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
}
