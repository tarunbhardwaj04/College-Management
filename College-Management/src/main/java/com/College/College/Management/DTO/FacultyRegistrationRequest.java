package com.College.College.Management.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FacultyRegistrationRequest extends BaseDto{
    private String department;  
}
