package com.College.College.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.College.College.Management.Entity.Department;

import java.util.Optional;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Optional<Department> findByName(String name);
}
