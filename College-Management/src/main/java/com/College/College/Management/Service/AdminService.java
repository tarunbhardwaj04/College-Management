package com.College.College.Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.AdminRegistrationRequest;
import com.College.College.Management.Entity.Admin;
import com.College.College.Management.Repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(AdminRegistrationRequest adminRegistrationRequest) {
        Admin admin = Admin.builder()
                .username(adminRegistrationRequest.getName())
                .email(adminRegistrationRequest.getEmail())
                .password(adminRegistrationRequest.getPassword())
                .phoneNumber(adminRegistrationRequest.getPhoneNumber())
                .address(adminRegistrationRequest.getAddress())
                .gender(adminRegistrationRequest.getGender())
                .department(adminRegistrationRequest.getDepartment())
                .build();
        return adminRepository.save(admin);
    }
}
