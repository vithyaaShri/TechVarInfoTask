package com.example.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
        private Long id;
        private String departmentName;
        private String departmentCode;
        private String departmentDescription;
}
