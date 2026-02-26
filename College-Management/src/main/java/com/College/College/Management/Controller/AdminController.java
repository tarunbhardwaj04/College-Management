package com.College.College.Management.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.College.College.Management.Entity.Admin;
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

    @DeleteMapping("/delete-department")
    public void deleteDepartment(@RequestBody Department department) {
        adminService.deleteDepartment(department);
    }

    @DeleteMapping("/delete-course")
    public void deleteCourse(@RequestParam UUID id) {
        adminService.deleteCourse(id);
    }

    @DeleteMapping("/delete-student")
    public void deleteStudent(@RequestBody Student student) {
        adminService.deleteStudent(student);
    }

    @DeleteMapping("/delete-faculty")
    public void deleteFaculty(@RequestBody Faculty faculty) {
        adminService.deleteFaculty(faculty);
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
    public List<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    @GetMapping("/get-all-faculties")
    public List<Faculty> getAllFaculties() {
        return adminService.getAllFaculties();
    }

    @GetMapping("/get-all-courses")
    public List<Course> getAllCourses() {
        return adminService.getAllCourses();
    }

    @GetMapping("/get-all-departments")
    public List<Department> getAllDepartments() {
        return adminService.getAllDepartments();
    }

    @GetMapping("/get-all-admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/add-course")
    public Course addCourse(@RequestBody Course course) {
        return adminService.addCourse(course);
    }
}
