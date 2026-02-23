package com.service;

import com.resourse.RegistrationRequest;

public interface UserService {
    String registerUser(RegistrationRequest request);
    String loginUser(String username, String password);
}