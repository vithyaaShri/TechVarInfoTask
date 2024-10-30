package com.example.departmentservice.service.serviceImpl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exceptionHandling.ListIsEmptyException;
import com.example.departmentservice.exceptionHandling.ResourceNotFoundException;
import com.example.departmentservice.exceptionHandling.ResourceWithDepartmentCodeAlreadyExist;
import com.example.departmentservice.exceptionHandling.ResourceWithDepartmentCodeNotFoundException;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    //To get Department based on DepartmentId form database
    @Override
    public DepartmentDto getDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Deaprtment","id",id));
        return modelMapper.map(department, DepartmentDto.class);
    }
    //To get all Department form database
    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty()){
            throw new ListIsEmptyException("List is empty");
        }
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        for (Department department : departments) {
            departmentDtos.add(modelMapper.map(department, DepartmentDto.class));
        }

        return departmentDtos;
    }
    //To save an Department Detail in db
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = Optional.ofNullable(departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode()));
        if(optionalDepartment.isPresent()){
            throw new ResourceWithDepartmentCodeAlreadyExist("Department Code Already Exist");
        }
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment=departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }
    //To update an Department Based on its ID
    @Override
    public DepartmentDto updateDepartment(Long id,DepartmentDto departmentDto) {
        Department department=departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department","id",id));
        assert department != null;
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department updatedDepartment=departmentRepository.save(department);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    //To get Department Details based on DepartmentCode
    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department=departmentRepository.findByDepartmentCode(code);
        if(department==null){
            throw new ResourceWithDepartmentCodeNotFoundException("Department with code does Not Exist");
        }
        return modelMapper.map(department, DepartmentDto.class);
    }
    //To Delete Department detail from Database
    @Override
    public String deleteDepartment(Long id) {
        Department department=departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department","id",id));
        if(department!=null) {
            departmentRepository.deleteById(id);
        }


        return "Department Deleted Successfully";
    }
}
