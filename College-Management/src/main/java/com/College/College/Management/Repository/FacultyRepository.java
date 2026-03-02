package com.College.College.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Entity.Subject;

import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {

    Faculty findByEmail(String email);

    Faculty findBySubjects(Subject subject);
    
}
