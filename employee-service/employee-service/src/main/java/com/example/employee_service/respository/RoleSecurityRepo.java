package com.example.employee_service.respository;

import com.example.employee_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleSecurityRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
