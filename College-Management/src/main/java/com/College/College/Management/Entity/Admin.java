package com.College.College.Management.Entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Admin extends User {
    @ManyToMany
    @JoinTable(
    name = "admin_department",
    joinColumns = @JoinColumn(name = "admin_id"),
    inverseJoinColumns = @JoinColumn(name = "department_id")
)
    private Set<Department> departments; 
    
    private String phoneNumber;

    private String address;

    private String gender;
}
