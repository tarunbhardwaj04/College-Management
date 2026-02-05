package com.College.College.Management.DTO;

import java.util.UUID;

import lombok.Data;


@Data
public class BaseDto {
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String role;

    private String phoneNumber;

    private String address;
    
    private String gender;
}
