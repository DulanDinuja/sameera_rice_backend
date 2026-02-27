package com.service;

import com.domain.User;
import com.resourse.RegistrationRequest;

import java.util.Map;

public interface UserService {
    String registerUser(RegistrationRequest request);
    Map<String, Object> loginUser(String username, String password);
    Map<String, Object> getUserDetails(String username);
}