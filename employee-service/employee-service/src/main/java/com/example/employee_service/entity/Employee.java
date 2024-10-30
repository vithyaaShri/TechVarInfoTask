package com.example.employee_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String firstName;
    private String lastName;
    @Column(unique=true,nullable=false)
    private String email;
    @Column(unique=true,nullable=false)
    private String phoneNo;
    private String address;
    @Column(unique=true,nullable=false)
    private String departmentCode;
    @Column(unique=true,nullable=false)
    private Long payRollId;

}
