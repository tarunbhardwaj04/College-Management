package com.College.College.Management.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.DepartmentResponse;
import com.College.College.Management.Entity.Department;
import com.College.College.Management.Repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Department addDepartment(Department department) {
        Department availableDepartment = departmentRepository.findByName(department.getName())
            .orElse(null);
        if (availableDepartment != null) {
            throw new RuntimeException("Department already exists");
        }
        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Department department) {
        Department availableDepartment = departmentRepository.findById(department.getId())
            .orElseThrow(() -> new RuntimeException("Department not found"));
        Department updatedDepartment = Department.builder()
            .id(availableDepartment.getId())
            .name(department.getName())
            .build();
        return departmentRepository.save(updatedDepartment);
    }

    @Transactional
    public Department deleteDepartment(UUID id) {
        Department availableDepartment = departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(availableDepartment);
        return availableDepartment;
    }

    @Transactional
    public Department getDepartment(UUID id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Transactional
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> DepartmentResponse.builder()
            .id(department.getId())
            .name(department.getName())
            .faculties(department.getFaculties().stream().map(faculty -> faculty.getUsername()).collect(Collectors.toSet()))
            .students(department.getStudents().stream().map(student -> student.getUsername()).collect(Collectors.toSet()))
            .admins(department.getAdmins().stream().map(admin -> admin.getUsername()).collect(Collectors.toSet()))
            .courses(department.getCourses().stream().map(course -> course.getName()).collect(Collectors.toSet()))
            .build()).collect(Collectors.toList());
    }
}
