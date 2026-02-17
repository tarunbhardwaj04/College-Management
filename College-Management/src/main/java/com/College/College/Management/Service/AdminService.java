package com.College.College.Management.Service;

import java.util.HashSet;
import java.util.List;
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
import com.College.College.Management.Repository.DepartmentRepository;
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
    private CourseService courseService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentService studentService;

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

    public Department addDepartment(Department department) {
        return departmentService.addDepartment(department);
    }

    @Transactional
    public Faculty updateFaculty(Faculty faculty) {
        return facultyService.updateFaculty(faculty);
    }   

    @Transactional
    public Student updateStudent(Student student) {
        return studentService.updateStudent(student);
    }   

    public Course updateCourse(Course course) {
        return courseService.updateCourse(course);
    }   

    public Department updateDepartment(Department department) {
        return departmentService.updateDepartment(department);
    }

    public Department deleteDepartment(Department department) {
        return departmentService.deleteDepartment(department.getId());
    }

    public Course deleteCourse(UUID id) {
        return courseService.deleteCourse(id);
    }

    public Student deleteStudent(Student student) {
        return studentService.deleteStudent(student.getId());
    }   

    public Faculty deleteFaculty(Faculty faculty) {
        return facultyService.deleteFaculty(faculty.getId());
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}