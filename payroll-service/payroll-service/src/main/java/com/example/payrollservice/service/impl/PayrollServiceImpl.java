package com.example.payrollservice.service.impl;

import com.example.payrollservice.dto.PayrollDto;
import com.example.payrollservice.entity.Payroll;
import com.example.payrollservice.exceptionHandling.ListIsEmptyException;
import com.example.payrollservice.exceptionHandling.ResourceNotFoundException;
import com.example.payrollservice.repository.PayrollRepository;
import com.example.payrollservice.service.PayrollService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PayrollServiceImpl implements PayrollService {
    private PayrollRepository payrollRepository;
    private ModelMapper modelMapper;

    //To get a Payroll Detail from a database
    @Override
    public PayrollDto getPayroll(Long payrollId) {
        Payroll payroll=payrollRepository.findById(payrollId).orElseThrow(()-> new ResourceNotFoundException("PayRoll","id",payrollId));

        return modelMapper.map(payroll, PayrollDto.class);
    }
    //To get all the payroll detail in a List from Database
    @Override
    public List<PayrollDto> getAllPayrolls() {
        List<Payroll> payrolls=payrollRepository.findAll();
        if(payrolls.isEmpty()){
            throw new ListIsEmptyException("PayRoll List is empty");
        }
        List<PayrollDto> payrollDtos=new ArrayList<>();
        for(Payroll payroll:payrolls){
            payrollDtos.add(modelMapper.map(payroll, PayrollDto.class));
        }
        return payrollDtos;
    }
    //To add a payroll Detail in Database
    @Override
    public PayrollDto createPayroll(PayrollDto payrollDto) {
        Payroll payroll = modelMapper.map(payrollDto, Payroll.class);
        Payroll savedPayroll = payrollRepository.save(payroll);
        return modelMapper.map(savedPayroll, PayrollDto.class);
    }

    //To Update the Payroll Detail in database based on Id
    @Override
    public PayrollDto updatePayroll(Long id, PayrollDto payrollDto) {
        Payroll payroll=payrollRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("PayRoll","id",id));
        assert payroll != null;
        payroll.setSalary(payrollDto.getSalary());
        payroll.setPayDate(payrollDto.getPayDate());
        Payroll savedPayroll=payrollRepository.save(payroll);
        return modelMapper.map(savedPayroll, PayrollDto.class);
    }


    //To delete a payroll from database
    @Override
    public void deletePayroll(Long payrollId) {
        Payroll payroll=payrollRepository.findById(payrollId).orElseThrow(()-> new ResourceNotFoundException("PayRoll","id",payrollId));
        assert payroll != null;
        payrollRepository.delete(payroll);


    }


}
