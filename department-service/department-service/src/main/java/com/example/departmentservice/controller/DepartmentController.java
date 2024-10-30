package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")

public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //EndPoint to Get the Department Detail Based on DepartmentId
    @GetMapping("/get/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id) {
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);

    }

    //EndPoint to Get All the DepartmentDetail in a List
    @GetMapping("/getAllDepartment")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    //EndPoint to Update the Department Based on Department Id
    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,@RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(id, departmentDto);
        return new ResponseEntity<>(updatedDepartmentDto, HttpStatus.OK);
    }

    //EndPoint to Get the Department Based on Department Code
    @GetMapping("/getByCode/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String code) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    //EndPoint to Delete Department Based On Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.accepted().body("Department deleted successfully");
    }

    //EndPoint to Save Department
    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentDto1 = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(departmentDto1,HttpStatus.CREATED);
    }

}
