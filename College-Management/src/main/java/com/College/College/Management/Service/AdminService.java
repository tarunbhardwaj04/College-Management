package com.College.College.Management.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
import com.College.College.Management.Entity.Course;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Entity.Student;
import com.College.College.Management.Entity.Department;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Repository.AdminRepository;
import com.College.College.Management.Repository.RoleRepository;
import com.College.College.Management.Repository.StudentRepository;
import com.College.College.Management.Repository.DepartmentRepository;
import com.College.College.Management.Repository.FacultyRepository;
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
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private CourseService courseService;

    public Admin registerAdmin(AdminRegistrationRequest adminRegistrationRequest, HashSet<Role> roles,
            HashSet<Department> departments) {
        String roleName = "ROLE_" + adminRegistrationRequest.getRole().toUpperCase();
        Role role = roleRepository.findByName(roleName);
        roles.add(role);

        Set<String> departmentNames = adminRegistrationRequest.getDepartment();
        if (departmentNames == null || departmentNames.isEmpty()) {
            departmentNames = Set.of("General");
        }

        for (String deptName : departmentNames) {
            Department department = departmentRepository.findByName(deptName)
                    .orElseGet(() -> departmentRepository.save(Department.builder().name(deptName).build()));
            departments.add(department);
        }

        Admin admin = Admin.builder()
                .username(adminRegistrationRequest.getName())
                .email(adminRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(adminRegistrationRequest.getPassword()))
                .phoneNumber(adminRegistrationRequest.getPhoneNumber())
                .address(adminRegistrationRequest.getAddress())
                .gender(adminRegistrationRequest.getGender())
                .departments(departments)
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
                    loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Admin admin = adminRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return admin;

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    @Transactional
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Faculty updateFaculty(Faculty faculty) {
        Faculty availableFaculty = facultyRepository.findById(faculty.getId())
            .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Faculty updatedFaculty = Faculty.builder()
            .id(availableFaculty.getId())
            .username(faculty.getUsername())
            .email(faculty.getEmail())
            .phoneNumber(faculty.getPhoneNumber())
            .address(faculty.getAddress())
            .gender(faculty.getGender())
            .department(faculty.getDepartment())
            .roles(faculty.getRoles())
            .build();
        return facultyRepository.save(updatedFaculty);
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

    public Course updateCourse(Course course) {
        return courseService.updateCourse(course);
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
    public Department deleteDepartment(Department department) {
        Department availableDepartment = departmentRepository.findById(department.getId())
            .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(availableDepartment);
        return availableDepartment;
    }

    public Course deleteCourse(UUID id) {
        return courseService.deleteCourse(id);
    }

    public Student deleteStudent(Student student) {
        Student availableStudent = studentRepository.findById(student.getId())
            .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(availableStudent);
        return availableStudent;
    }   

    @Transactional
    public Faculty deleteFaculty(Faculty faculty) {
        Faculty availableFaculty = facultyRepository.findById(faculty.getId())
            .orElseThrow(() -> new RuntimeException("Faculty not found"));
        facultyRepository.delete(availableFaculty);
        return availableFaculty;
    }
}