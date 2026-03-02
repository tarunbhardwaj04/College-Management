package com.College.College.Management.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.College.College.Management.DTO.FacultyRegistrationRequest;
import com.College.College.Management.DTO.LoginRequest;
import com.College.College.Management.DTO.LoginResponse;
import com.College.College.Management.Security.JwtUtils;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Entity.Role;
import com.College.College.Management.Entity.Subject;
import com.College.College.Management.Repository.RoleRepository;
import com.College.College.Management.Repository.FacultyRepository;
import com.College.College.Management.Repository.SubjectRepository;
import jakarta.transaction.Transactional;

@Service
public class FacultyService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public Faculty registerFaculty(FacultyRegistrationRequest facultyRegistrationRequest, HashSet<Role> roles) {
        String roleName = "ROLE_" + facultyRegistrationRequest.getRole().toUpperCase();
        Role role = roleRepository.findByName(roleName);
        roles.add(role);
        Faculty faculty = Faculty.builder()
                .username(facultyRegistrationRequest.getName())
                .email(facultyRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(facultyRegistrationRequest.getPassword()))
                .phoneNumber(facultyRegistrationRequest.getPhoneNumber())
                .address(facultyRegistrationRequest.getAddress())
                .gender(facultyRegistrationRequest.getGender())
                .department(facultyRegistrationRequest.getDepartment())
                .roles(roles)
                .build();
        return facultyRepository.save(faculty);
    }

    @Transactional
    public ResponseEntity<?> facultyLogin(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Faculty faculty = facultyRepository.findByEmail(loginRequest.getEmail());

            String token = jwtUtils.generateToken(faculty.getEmail(), authentication.getAuthorities());

            LoginResponse response = new LoginResponse(
                    faculty.getId(),
                    faculty.getUsername(),
                    faculty.getEmail(),
                    faculty.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                    token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }

    @Transactional
    public Faculty updateFaculty(Faculty faculty) {
        Faculty availableFaculty = facultyRepository.findById(faculty.getId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Faculty updatedFaculty = Faculty.builder()
                .id(availableFaculty.getId())
                .username(faculty.getUsername())
                .email(faculty.getEmail())
                .phoneNumber(faculty.getPhoneNumber())
                .address(faculty.getAddress())
                .gender(faculty.getGender())
                .department(faculty.getDepartment())
                .roles(faculty.getRoles())
                .build();
        return facultyRepository.save(updatedFaculty);
    }

    @Transactional
    public Faculty deleteFaculty(UUID id) {
        Faculty availableFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        facultyRepository.delete(availableFaculty);
        return availableFaculty;
    }

    @Transactional
    public Faculty getFaculty(UUID id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    @Transactional
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Transactional
    public Faculty getFacultyBySubject(String subject) {
        Subject existingSubject = subjectRepository.findByName(subject).orElseThrow(() -> new RuntimeException("Subject not found"));
        return facultyRepository.findBySubjects(existingSubject);
    }

    @Transactional
    public Faculty getFacultyById(UUID id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }
}
