package com.example.payrollservice.controller;

import com.example.payrollservice.dto.PayrollDto;
import com.example.payrollservice.service.PayrollService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payroll")
public class PayRollController {
    private PayrollService payrollService;
    //EndPoint to get Payroll detail based on PayrollId
    @GetMapping("/getPayroll/{id}")
    public ResponseEntity<PayrollDto> getPayroll(@PathVariable Long id) {
        PayrollDto payrollDto=payrollService.getPayroll(id);
        return new ResponseEntity<>(payrollDto, HttpStatus.OK);
    }
    //End Point To get All Payroll detail
    @GetMapping("/getAllPayroll")
    public ResponseEntity<List<PayrollDto>> getAllPayroll() {
        List<PayrollDto> payrollDtos=payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrollDtos);
    }

    //EndPoint to save Payroll in database
    @PostMapping("/savePayroll")
    public ResponseEntity<PayrollDto> savePayroll(@Valid @RequestBody PayrollDto payrollDto) {
        PayrollDto payrollDto1=payrollService.createPayroll(payrollDto);
        return new ResponseEntity<>(payrollDto1, HttpStatus.CREATED);
    }
    //EndPoint to update Employee Based on the id
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<PayrollDto> updateEmployee(@PathVariable Long id,@Valid @RequestBody  PayrollDto payrollDto) {
        PayrollDto updatedPayroll=payrollService.updatePayroll(id, payrollDto);
        return new ResponseEntity(updatedPayroll, HttpStatus.OK);
    }
    //EndPoint to delete Employee based on Employee Id
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return new ResponseEntity("Employee deleted", HttpStatus.OK);
    }
}
