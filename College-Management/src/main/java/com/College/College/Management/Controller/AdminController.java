package com.College.College.Management.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.College.College.Management.DTO.AdminResponse;
import com.College.College.Management.DTO.CourseRequest;
import com.College.College.Management.DTO.CourseResponse;
import com.College.College.Management.DTO.DepartmentResponse;
import com.College.College.Management.Entity.Course;
import com.College.College.Management.Entity.Department;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Entity.Student;
import com.College.College.Management.Service.AdminService;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PutMapping("/add-department")
    public Department addDepartment(@RequestBody Department department) {
        return adminService.addDepartment(department);
    }

    @DeleteMapping("/delete-department/{id}")
    public void deleteDepartment(@PathVariable UUID id) {
        adminService.deleteDepartment(id);
    }

    @DeleteMapping("/delete-course/{id}")
    public void deleteCourse(@PathVariable UUID id) {
        adminService.deleteCourse(id);
    }

    @DeleteMapping("/delete-student/{id}")
    public void deleteStudent(@PathVariable UUID id) {
        adminService.deleteStudent(id);
    }

    @DeleteMapping("/delete-faculty/{id}")
    public void deleteFaculty(@PathVariable UUID id) {
        adminService.deleteFaculty(id);
    }

    @PutMapping("/update-department")
    public Department updateDepartment(@RequestBody Department department) {
        return adminService.updateDepartment(department);
    }

    @PutMapping("/update-course")
    public Course updateCourse(@RequestBody Course course) {
        return adminService.updateCourse(course);
    }

    @PutMapping("/update-student")
    public Student updateStudent(@RequestBody Student student) {
        return adminService.updateStudent(student);
    }

    @PutMapping("/update-faculty")
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return adminService.updateFaculty(faculty);
    }

    @GetMapping("/get-all-students")
    public Page<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    @GetMapping("/get-all-faculties")
    public Page<Faculty> getAllFaculties() {
        return adminService.getAllFaculties();
    }

    @GetMapping("/get-all-courses")
    public Page<Course> getAllCourses() {
        return adminService.getAllCourses();
    }

    @GetMapping("/get-all-departments")
    public Page<DepartmentResponse> getAllDepartments() {
        return adminService.getAllDepartments();
    }

    @GetMapping("/get-all-admins")
    public Page<AdminResponse> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/add-course")
    public CourseResponse addCourse(@RequestBody CourseRequest courseRequest) {
        return adminService.addCourse(courseRequest);
    }
}
