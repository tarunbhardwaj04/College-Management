package com.College.College.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.College.College.Management.Entity.Faculty;
import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
    
}
