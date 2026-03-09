package com.College.College.Management.DTO;

import lombok.Data;

@Data
public class CourseRequest {
    private String name;

    private Integer semester;

    private Double fees;

    private String departmentName;

    private Double duration;
    
    private String facultyname;
}
