package com.College.College.Management.DTO;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UUID id;
    private String username;
    private String email;
    private Set<String> roles;
    private String token;
}
