package com.College.College.Management.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.LoginRequest;
import com.College.College.Management.DTO.StudentRegistrationRequest;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Entity.Student;
import com.College.College.Management.Repository.RoleRepository;
import com.College.College.Management.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    private AuthenticationManager authenticationManager;    
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoleRepository roleRepository;
    public Student registerStudent(StudentRegistrationRequest studentRegistrationRequest,HashSet<Role> roles) {
        Role role=roleRepository.findByName("STUDENT");
        roles.add(role);    
        Student student = Student.builder()
                .username(studentRegistrationRequest.getName())
                .email(studentRegistrationRequest.getEmail())
                .password(studentRegistrationRequest.getPassword())
                .phoneNumber(studentRegistrationRequest.getPhoneNumber())
                .address(studentRegistrationRequest.getAddress())
                .gender(studentRegistrationRequest.getGender())
                .batch(studentRegistrationRequest.getBatch())
                .rollNumber(studentRegistrationRequest.getRollNumber())
                .registrationNumber(studentRegistrationRequest.getRegistrationNumber())
                .roles(roles)
                .build();
        return studentRepository.save(student);
    }

    @Transactional
    public ResponseEntity<?> studentLogin(LoginRequest loginRequest) {
        try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), 
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Student student = studentRepository.findByEmail(loginRequest.getEmail());
        
        return ResponseEntity.ok(student);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }

    @Transactional
    public Student updateStudent(Student student) {
        Student availableStudent = studentRepository.findById(student.getId())
            .orElseThrow(() -> new RuntimeException("Student not found"));
        Student updatedStudent = Student.builder()
            .id(availableStudent.getId())
            .username(student.getUsername())
            .email(student.getEmail())
            .phoneNumber(student.getPhoneNumber())
            .address(student.getAddress())
            .gender(student.getGender())
            .department(student.getDepartment())
            .roles(student.getRoles())
            .build();
        return studentRepository.save(updatedStudent);
    }   

    @Transactional
    public Student deleteStudent(UUID id) {
        Student availableStudent = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(availableStudent);
        return availableStudent;
    }

    @Transactional
    public Student getStudent(UUID id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Transactional
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
