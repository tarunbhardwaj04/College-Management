package com.College.College.Management.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.CourseRequest;
import com.College.College.Management.Entity.Course;
import com.College.College.Management.Entity.Department;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Repository.CourseRepository;
import com.College.College.Management.Repository.DepartmentRepository;
import com.College.College.Management.Repository.FacultyRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
public Course addCourse(CourseRequest courseRequest) {
    if (courseRepository.findByName(courseRequest.getName()) != null) {
        throw new RuntimeException("Course with name " + courseRequest.getName() + " already exists");
    }
    Department department = departmentRepository.findByName(courseRequest.getDepartmentName())
            .orElseThrow(() -> new RuntimeException("Department not found: " + courseRequest.getDepartmentName()));

    List<Faculty> facultyList = facultyRepository.findByUsernameInAndDepartment_Name(
            courseRequest.getFacultyName(), 
            courseRequest.getDepartmentName()
    );
    if (facultyList.size() != courseRequest.getFacultyName().size()) {
        throw new RuntimeException("Some faculties were not found in " + courseRequest.getDepartmentName());
    }

    try {
        Course course = Course.builder()
                .name(courseRequest.getName())
                .code(courseRequest.getCode()) 
                .semester(courseRequest.getSemester())
                .fee(courseRequest.getFee())
                .duration(courseRequest.getDuration())
                .department(department)
                .faculties(facultyList)
                .build();

        return courseRepository.save(course);
        
    } catch (Exception e) {
        throw new RuntimeException("Database error while saving course: " + e.getMessage());
    }
}

    @Transactional
    public Course updateCourse(Course course) {
        try{
            Course availableCourse = courseRepository.findByName(course.getName());
        Course updatedCourse = Course.builder()
            .id(availableCourse.getId())
            .name(course.getName())
            .duration(course.getDuration())
            .code(course.getCode())
            .fee(course.getFee())
            .build();
        return courseRepository.save(updatedCourse);
        }catch(Exception e){
            throw new RuntimeException("Failed to update course: " + e.getMessage());
        }
    }

    @Transactional
    public Course deleteCourse(UUID id) {
        Course availableCourse = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(availableCourse);
        return availableCourse;
    }

    @Transactional
    public Course getCourse(UUID id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Transactional
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
