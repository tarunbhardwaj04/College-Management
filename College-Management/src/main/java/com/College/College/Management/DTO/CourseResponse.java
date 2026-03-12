package com.College.College.Management.DTO;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private UUID id;
    private String name;
    private String code;
    private Integer semester;
    private Double fee;
    private Double duration;
    private String departmentName;
    private List<String> facultyName;
}
