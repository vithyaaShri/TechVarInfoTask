package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    public DepartmentDto getDepartment(Long id);
    public List<DepartmentDto> getAllDepartments();
    public DepartmentDto saveDepartment(DepartmentDto departmentDto);
    public DepartmentDto updateDepartment(Long id,DepartmentDto departmentDto);
    public DepartmentDto getDepartmentByCode(String code);
    public String deleteDepartment(Long id);

}
