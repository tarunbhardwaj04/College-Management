package com.College.College.Management.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.College.College.Management.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    String findByName(String name);
}
