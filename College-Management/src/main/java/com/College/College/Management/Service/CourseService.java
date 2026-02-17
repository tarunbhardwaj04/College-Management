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
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Course course) {
        Course availableCourse = courseRepository.findById(course.getId())
            .orElseThrow(() -> new RuntimeException("Course not found"));
        Course updatedCourse = Course.builder()
            .id(availableCourse.getId())
            .name(course.getName())
            .build();
        return courseRepository.save(updatedCourse);
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
