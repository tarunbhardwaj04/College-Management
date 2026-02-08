package com.College.College.Management.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.DTO.LoginRequest;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Repository.RoleRepository;
import com.College.College.Management.Repository.FacultyRepository;

@Service
public class FacultyService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;    
    @Autowired  
    private RoleRepository roleRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest,HashSet<Role> roles) {
        String roleName = "ROLE_" + facultyRegistrationRequest.getRole().toUpperCase();
        Role role=roleRepository.findByName(roleName);
        roles.add(role);
        Faculty faculty = Faculty.builder()
                .username(facultyRegistrationRequest.getName())
                .email(facultyRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(facultyRegistrationRequest.getPassword()))
                .phoneNumber(facultyRegistrationRequest.getPhoneNumber())
                .address(facultyRegistrationRequest.getAddress())
                .gender(facultyRegistrationRequest.getGender())
                .department(facultyRegistrationRequest.getDepartment())
                .roles(roles)
                .build();
        return facultyRepository.save(faculty);
    }

    public ResponseEntity<?> facultyLogin(LoginRequest loginRequest) {
        try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), 
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Faculty faculty = facultyRepository.findByEmail(loginRequest.getEmail());
        
        return ResponseEntity.ok(faculty);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }   
}
