package com.College.College.Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.College.College.Management.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
