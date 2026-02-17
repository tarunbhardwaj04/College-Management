package com.College.College.Management.DTO;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminRegistrationRequest extends BaseDto{
    private Set<String> department;  
}
    