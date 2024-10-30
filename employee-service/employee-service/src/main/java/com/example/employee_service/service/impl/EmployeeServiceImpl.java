package com.example.employee_service.service.impl;

import com.example.employee_service.dto.*;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.entity.EmployeeV2;
import com.example.employee_service.exceptionHandling.EmailAlreadyExsist;
import com.example.employee_service.exceptionHandling.ListIsEmptyException;
import com.example.employee_service.exceptionHandling.ResourceNotFoundException;
import com.example.employee_service.exceptionHandling.ResourceWithEmailIdNotFoundException;
import com.example.employee_service.respository.EmployeeRepository;
import com.example.employee_service.respository.EmployeeRepositoryV2;
import com.example.employee_service.service.ApiClient;
import com.example.employee_service.service.ApiClientPayroll;
import com.example.employee_service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    private ApiClient apiClient;
    private ApiClientPayroll apiClientPayRoll;
    @Autowired
    private EmployeeRepositoryV2 employeeRepositoryV2;

    //AddEmployee Business Logic for Version 2
    @Override
    public EmployeeDtoV2 addEmployeeV2(EmployeeDtoV2 employee) {
        Optional<EmployeeV2> employeeV2EmailCheck= Optional.ofNullable(employeeRepositoryV2.findByEmail(employee.getEmail()));
        if(employeeV2EmailCheck.isPresent()){
            throw new EmailAlreadyExsist("Email Already Exist");
        }
        EmployeeV2 employeeV2=modelMapper.map(employee, EmployeeV2.class);
        EmployeeV2 savedEmployee=employeeRepositoryV2.save(employeeV2);
        return modelMapper.map(savedEmployee, EmployeeDtoV2.class);
    }

    //Get Employee to get the details of Employee based on EmployeeId Provided. It is going to fetch data
    // from department microservice using feign client communication and Also going to fetch PayRoll information
    // from payroll microservice. This Method will display the combined information about Employee and its department detail
    //and Payroll Detail.

    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        Employee employee =employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        EmployeeDto employeeDto=modelMapper.map(employee,EmployeeDto.class);
        assert employee != null;
        DepartmentDto departmentDto=apiClient.getDepartmentByCode(employee.getDepartmentCode());
        PayrollDto payrollDto=apiClientPayRoll.getPayroll(employeeDto.getPayRollId());
        ApiResponseDto apiResponseDto=new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setPayrollDto(payrollDto);
        return apiResponseDto;
    }

    //Get Employee to get the details of all Employee in a List. It is going to fetch data
    // from department microservice using feign client communication and Also going to fetch PayRoll information
    // from payroll microservice. This Method will display the combined information about Employee and its department detail
    //and Payroll Detail in a List.

    @Override
    public List<ApiResponseDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new ListIsEmptyException("List is Empty");
        }
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<ApiResponseDto> apiResponseDtoList=new ArrayList<ApiResponseDto>();
        for (Employee employee : employees) {
            employeeDtos.add(modelMapper.map(employee, EmployeeDto.class));

        }
        for (EmployeeDto employeeDto : employeeDtos) {
            DepartmentDto departmentDto=apiClient.getDepartmentByCode(employeeDto.getDepartmentCode());
            PayrollDto payrollDto=apiClientPayRoll.getPayroll(employeeDto.getPayRollId());
            if(departmentDto==null){
                Long employeeId= (long) employeeDto.getEmployeeId();
                throw new ResourceNotFoundException("Department","id", employeeId);
            }
            ApiResponseDto apiResponseDto1=new ApiResponseDto();
            apiResponseDto1.setEmployeeDto(employeeDto);
            apiResponseDto1.setDepartmentDto(departmentDto);
            apiResponseDto1.setPayrollDto(payrollDto);
            apiResponseDtoList.add(apiResponseDto1);
        }

        return apiResponseDtoList;
    }

    //AddEmployee is going to add the Employee detail to database
    @Override
    public EmployeeDto addEmployee(EmployeeDto employee) {
        Optional<Employee> employeeEmailCheck= Optional.ofNullable(employeeRepository.findByEmail(employee.getEmail()));
        if(employeeEmailCheck.isPresent()){
            throw new EmailAlreadyExsist("Email Already Exist");
        }
        Employee employee1=modelMapper.map(employee, Employee.class);
        Employee savedEmployee=employeeRepository.save(employee1);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    //Update Employee is going to update the Employee details based on id and Request body send by Client.
    @Override
    public EmployeeDto updateEmployee(Long id,EmployeeDto employee) {
        Employee employee1=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        assert employee1 != null;
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmail(employee.getEmail());
        employee1.setPhoneNo(employee.getPhoneNo());
        Employee savedEmployee=employeeRepository.save(employee1);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    //Each Employee Email Id is unique so going to fetch employee detail based on EmailId
    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if(employee==null){
            throw new ResourceWithEmailIdNotFoundException("Employee With Email Id not Found");
        }

        return modelMapper.map(employee, EmployeeDto.class);
    }

    //Delete employee going to delete the Employee details from database Based on Employee Id Provided.
    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeCheck= Optional.ofNullable(employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id)));
        if(employeeCheck.isPresent()){
            employeeRepository.deleteById(id);
        }


    }
}
