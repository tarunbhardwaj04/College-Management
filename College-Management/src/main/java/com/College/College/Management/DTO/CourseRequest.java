package com.College.College.Management.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CourseRequest {
    private String name;

    private Integer semester;

    private Double fee;

    private String code;

    private String departmentName;

    private Double duration;
    
    private List<String> facultyName;
}
