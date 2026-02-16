package com.College.College.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.College.College.Management.DTO.AdminRegistrationRequest;
import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.DTO.LoginRequest;
import com.College.College.Management.DTO.StudentRegistrationRequest;
import com.College.College.Management.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationRequest studentRegistrationRequest) {
        return userService.registerStudent(studentRegistrationRequest);
    }

    @PostMapping("/register/faculty")
    public ResponseEntity<?> registerFaculty(@RequestBody FacultyRegistrationRequest facultyRegistrationRequest) {
        return userService.registerFaculty(facultyRegistrationRequest);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminRegistrationRequest adminRegistrationRequest) {
        return userService.registerAdmin(adminRegistrationRequest);
    }   

    @PostMapping("/login/admin")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest loginRequest) {
        return userService.adminLogin(loginRequest);
    }

    @PostMapping("/login/faculty")
    public ResponseEntity<?> facultyLogin(@RequestBody LoginRequest loginRequest) {
        return userService.facultyLogin(loginRequest);
    }

    @PostMapping("/login/student")
    public ResponseEntity<?> studentLogin(@RequestBody LoginRequest loginRequest) {
        return userService.studentLogin(loginRequest);
    }
}
