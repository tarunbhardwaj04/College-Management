package com.College.College.Management.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentRegistrationRequest extends BaseDto{

    private String batch;

    private String rollNumber;

    private String registrationNumber;
}
