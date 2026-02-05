package com.College.College.Management.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Repository.RoleRepository;
import com.College.College.Management.Repository.FacultyRepository;

@Service
public class FacultyService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest,HashSet<Role> roles) {
        String roleName = "ROLE_" + facultyRegistrationRequest.getRole().toUpperCase();
        Role role=roleRepository.findByName(roleName);
        roles.add(role);
        Faculty faculty = Faculty.builder()
                .username(facultyRegistrationRequest.getName())
                .email(facultyRegistrationRequest.getEmail())
                .password(facultyRegistrationRequest.getPassword())
                .phoneNumber(facultyRegistrationRequest.getPhoneNumber())
                .address(facultyRegistrationRequest.getAddress())
                .gender(facultyRegistrationRequest.getGender())
                .department(facultyRegistrationRequest.getDepartment())
                .roles(roles)
                .build();
        return facultyRepository.save(faculty);
    }
}
