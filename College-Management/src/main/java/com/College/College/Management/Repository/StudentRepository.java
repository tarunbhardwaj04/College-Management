package com.College.College.Management.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.College.College.Management.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    
}
