package com.College.College.Management.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminRegistrationRequest extends BaseDto{
    private String address;
    
    private String gender;
    
    private String department;  
}
