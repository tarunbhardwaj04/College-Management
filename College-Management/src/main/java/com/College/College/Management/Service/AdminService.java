package com.College.College.Management.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.AdminRegistrationRequest;
import com.College.College.Management.Entity.Admin;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Repository.AdminRepository;
import com.College.College.Management.Repository.RoleRepository;

@Service
public class AdminService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(AdminRegistrationRequest adminRegistrationRequest,HashSet<Role> roles) {
        Role role=roleRepository.findByName("ADMIN");
        roles.add(role);
        Admin admin = Admin.builder()
                .username(adminRegistrationRequest.getName())
                .email(adminRegistrationRequest.getEmail())
                .password(adminRegistrationRequest.getPassword())
                .phoneNumber(adminRegistrationRequest.getPhoneNumber())
                .address(adminRegistrationRequest.getAddress())
                .gender(adminRegistrationRequest.getGender())
                .department(adminRegistrationRequest.getDepartment())
                .roles(roles)
                .build();
        return adminRepository.save(admin);
    }
}
