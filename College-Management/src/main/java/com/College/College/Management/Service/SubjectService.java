package com.College.College.Management.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College.College.Management.Entity.Subject;
import com.College.College.Management.Repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

        public Subject addSubject(Subject subject) { 
            Subject existingSubject = subjectRepository.findByName(subject.getName())
                .orElseThrow(() -> new RuntimeException("Subject not found"));  
            return subjectRepository.save(existingSubject);
        }   

        public Subject updateSubject(UUID id, Subject subject) {
            Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
            Subject updatedSubject = Subject.builder()
                .id(existingSubject.getId())
                .name(subject.getName())
                .course(subject.getCourse())
                .faculties(subject.getFaculties())
                .build();  
            return subjectRepository.save(updatedSubject);
        }   

        public Subject deleteSubject(UUID id) {
            Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
            subjectRepository.delete(subject);
            return subject;
        }   

        public Subject getSubject(UUID id) {
            return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        }   

        public List<Subject> getAllSubjects() {
            return subjectRepository.findAll();
        }   
    
}
