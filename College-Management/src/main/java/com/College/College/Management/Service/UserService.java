package com.College.College.Management.Service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.AdminRegistrationRequest;
import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.DTO.LoginRequest;
import com.College.College.Management.DTO.StudentRegistrationRequest;
import com.College.College.Management.Entity.Department;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Entity.User;
import com.College.College.Management.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private AdminService adminService;

    public ResponseEntity<?> registerStudent(StudentRegistrationRequest studentRegistrationRequest) {

        Optional<User> existUser = userRepository.findByEmail(studentRegistrationRequest.getEmail());
        if (existUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.registerStudent(studentRegistrationRequest, new HashSet<Role>()));
    }

    public ResponseEntity<?> registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest) {
        Optional<User> existUser = userRepository.findByEmail(facultyRegistrationRequest.getEmail());
        if (existUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(facultyService.registerFaculty(facultyRegistrationRequest, new HashSet<Role>()));
    }

    public ResponseEntity<?> registerAdmin(AdminRegistrationRequest adminRegistrationRequest) {
        Optional<User> existUser = userRepository.findByEmail(adminRegistrationRequest.getEmail());
        if (existUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.registerAdmin(adminRegistrationRequest, new HashSet<Role>(),
                        new HashSet<Department>()));
    }

    public ResponseEntity<?> adminLogin(LoginRequest loginRequest) {
        Optional<User> existUser = userRepository.findByEmail(loginRequest.getEmail());
        if (existUser.isEmpty()) {
            throw new RuntimeException("User not found in user service");
        }
        return ResponseEntity.status(HttpStatus.OK).body(adminService.login(loginRequest));
    }

    public ResponseEntity<?> facultyLogin(LoginRequest loginRequest) {
        Optional<User> existUser = userRepository.findByEmail(loginRequest.getEmail());
        if (existUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return facultyService.facultyLogin(loginRequest);
    }

    public ResponseEntity<?> studentLogin(LoginRequest loginRequest) {
        Optional<User> existUser = userRepository.findByEmail(loginRequest.getEmail());
        if (existUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return studentService.studentLogin(loginRequest);
    }
}