package com.College.College.Management.Entity;

import java.util.UUID;

import com.College.College.Management.Enums.ResultStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double marks;

    private String grade;

    @Enumerated(EnumType.STRING)
    private ResultStatus resultStatus;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
}
