package com.College.College.Management.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.StudentRegistrationRequest;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Entity.Student;
import com.College.College.Management.Repository.RoleRepository;
import com.College.College.Management.Repository.StudentRepository;

@Service
public class StudentService {
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
}
