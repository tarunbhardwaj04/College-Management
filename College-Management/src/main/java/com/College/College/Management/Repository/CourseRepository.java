package com.College.College.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.College.College.Management.Entity.Course;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    
}
