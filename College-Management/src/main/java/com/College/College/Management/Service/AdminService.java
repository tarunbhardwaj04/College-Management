package com.College.College.Management.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.College.College.Management.DTO.AdminRegistrationRequest;
import com.College.College.Management.DTO.LoginRequest;
import com.College.College.Management.Entity.Admin;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Repository.AdminRepository;
import com.College.College.Management.Repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(AdminRegistrationRequest adminRegistrationRequest,HashSet<Role> roles) {
        String roleName = "ROLE_" + adminRegistrationRequest.getRole().toUpperCase();
        Role role=roleRepository.findByName(roleName);
        roles.add(role);
        Admin admin = Admin.builder()
                .username(adminRegistrationRequest.getName())
                .email(adminRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(adminRegistrationRequest.getPassword()))
                .phoneNumber(adminRegistrationRequest.getPhoneNumber())
                .address(adminRegistrationRequest.getAddress())
                .gender(adminRegistrationRequest.getGender())
                .department(adminRegistrationRequest.getDepartment())
                .roles(roles)
                .build();
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin login(LoginRequest loginRequest) {
        try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), 
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Admin admin = adminRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));


        
        return admin;

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }
}
