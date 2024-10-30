package com.example.employee_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")

//This Table is for Version2 (API Versioning)
public class EmployeeV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(unique=true,nullable=false)
    private String email;
    @Column(unique=true,nullable=false)
    private String phoneNo;
    private String address_line1;
    private String pinCode;
    @Column(unique=true,nullable=false)
    private String departmentCode;
    @Column(unique=true,nullable=false)
    private Long payRollId;

}
