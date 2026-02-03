package com.College.College.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.College.College.Management.Entity.Subject;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
        
}
