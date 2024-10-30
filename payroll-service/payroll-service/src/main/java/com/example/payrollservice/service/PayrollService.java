package com.example.payrollservice.service;

import com.example.payrollservice.dto.PayrollDto;

import java.util.List;

public interface PayrollService {
    PayrollDto createPayroll(PayrollDto payrollDto);
    PayrollDto updatePayroll(Long id,PayrollDto payrollDto);
    PayrollDto getPayroll(Long payrollId);
    void deletePayroll(Long payrollId);
    List<PayrollDto> getAllPayrolls();
}
