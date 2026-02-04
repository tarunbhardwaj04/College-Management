package com.College.College.Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Repository.FacultyRepository;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest) {
        Faculty faculty = Faculty.builder()
                .username(facultyRegistrationRequest.getName())
                .email(facultyRegistrationRequest.getEmail())
                .password(facultyRegistrationRequest.getPassword())
                .phoneNumber(facultyRegistrationRequest.getPhoneNumber())
                .address(facultyRegistrationRequest.getAddress())
                .gender(facultyRegistrationRequest.getGender())
                .department(facultyRegistrationRequest.getDepartment())
                .build();
        return facultyRepository.save(faculty);
    }
}
