package com.College.College.Management.DTO;

import lombok.Data;

@Data
public class CourseRequest {
    private String name;
    private String description;
    private Integer semester;
    private Double fees;
    private String departmentName;
}
