package com.example.employee_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoV2 {

    private int employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String address_line1;
    private String pinCode;
    private String departmentCode;
    private Long payRollId;


}
