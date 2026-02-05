    package com.College.College.Management.Service;
    import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.AdminRegistrationRequest;
import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.DTO.StudentRegistrationRequest;
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

            User existUser=userRepository.findByEmail(studentRegistrationRequest.getEmail());
            if(existUser!=null){
                throw new RuntimeException("User already exists");
            }   
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.registerStudent(studentRegistrationRequest,new HashSet<Role>()))  ;            
        }    

        public ResponseEntity<?> registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest) {
            User existUser=userRepository.findByEmail(facultyRegistrationRequest.getEmail());
            if(existUser!=null){
                throw new RuntimeException("User already exists");
            }   
            return ResponseEntity.status(HttpStatus.CREATED).body(facultyService.registerFaculty(facultyRegistrationRequest,new HashSet<Role>()))  ;            
        }    

        public ResponseEntity<?> registerAdmin(AdminRegistrationRequest adminRegistrationRequest) {
            User existUser=userRepository.findByEmail(adminRegistrationRequest.getEmail());
            if(existUser!=null){
                throw new RuntimeException("User already exists");
            }   
            return ResponseEntity.status(HttpStatus.CREATED).body(adminService.registerAdmin(adminRegistrationRequest,new HashSet<Role>()))  ;            
        }    
    }
