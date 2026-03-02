package com.College.College.Management.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.College.College.Management.Entity.Faculty;
import com.College.College.Management.Service.FacultyService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    
    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        return new ResponseEntity<>(facultyService.getAllFaculties(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable UUID id) {
        return new ResponseEntity<>(facultyService.getFacultyById(id), HttpStatus.OK);
    }
    
}
