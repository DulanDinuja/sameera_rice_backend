package com.service.impl;

import com.domain.User;
import com.repository.UserRepository;
import com.resourse.RegistrationRequest;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public String registerUser(RegistrationRequest request) {
        System.out.println("Password: " + request.getPassword());
        System.out.println("Confirm Password: " + request.getConfirmPassword());
        System.out.println("Passwords equal: " + request.getPassword().equals(request.getConfirmPassword()));
        
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            return "Password fields cannot be empty";
        }
        
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return "Passwords do not match";
        }
        
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }
        
        if (userRepository.existsByNic(request.getNic())) {
            return "NIC already exists";
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setNic(request.getNic());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public Map<String, Object> loginUser(String username, String password) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            response.put("error", "User not found");
            return response;
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = UUID.randomUUID().toString();
            response.put("access_token", token);
            response.put("user", getUserDetails(username));
            return response;
        } else {
            response.put("error", "Invalid credentials");
            return response;
        }
    }

    @Override
    public Map<String, Object> getUserDetails(String username) {
        User user = userRepository.findByUsername(username);
        Map<String, Object> userDetails = new HashMap<>();
        
        if (user != null) {
            userDetails.put("id", user.getId());
            userDetails.put("username", user.getUsername());
            userDetails.put("email", user.getEmail());
            userDetails.put("mobileNumber", user.getMobileNumber());
            userDetails.put("nic", user.getNic());
        }
        
        return userDetails;
    }
}