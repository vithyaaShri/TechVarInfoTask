package com.example.employee_service.respository;

import com.example.employee_service.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserSecurityRepo extends JpaRepository<UserSecurity,Long> {
    Optional<UserSecurity> findByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<UserSecurity> findByUsernameOrEmail(String username,String email);
    Boolean existsByUsername(String firstname);

}
