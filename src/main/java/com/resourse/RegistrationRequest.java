package com.resourse;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String email;
    private String mobileNumber;
    private String nic;
    private String password;
    private String confirmPassword;
}