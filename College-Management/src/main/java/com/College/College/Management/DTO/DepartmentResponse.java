package com.College.College.Management.DTO;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private UUID id;
    private String name;
    private Set<String> faculties;
    private Set<String> students;
    private Set<String> admins;
    private Set<String> courses;
    
}
