package com.College.College.Management.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.Entity.Course;
import com.College.College.Management.Repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public Course addCourse(Course course) {
        
        Course availableCourse = courseRepository.findByName(course.getName());
        if (availableCourse != null) {
            throw new RuntimeException("Course already exists");
        }
        try{
            return courseRepository.save(course);
        }catch(Exception e){
            throw new RuntimeException("Failed to add course: " + e.getMessage());
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
