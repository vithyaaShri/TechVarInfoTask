package com.example.employee_service.service;

import com.example.employee_service.entity.UserSecurity;

public interface UserSecurityService {
    UserSecurity getUser(String name);

    boolean oldPasswordIsValid(UserSecurity userSecurity,String oldPassword);
    void changePassword(UserSecurity userSecurity, String newPassword);
}
