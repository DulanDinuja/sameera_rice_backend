package com.controller;

import com.resourse.LoginRequest;
import com.resourse.RegistrationRequest;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        // Validate username
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }
        
        // Validate email
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        
        // Validate mobile number
        if (request.getMobileNumber() == null || request.getMobileNumber().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Mobile number is required");
        }
        
        // Validate NIC
        if (request.getNic() == null || request.getNic().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("NIC is required");
        }
        
        // Validate password
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }
        
        // Validate confirm password
        if (request.getConfirmPassword() == null || request.getConfirmPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Confirm password is required");
        }
        
        String result = userService.registerUser(request);
        if (result.equals("User registered successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Validate username
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            Map<String, Object> error = Map.of("error", "Username is required");
            return ResponseEntity.badRequest().body(error);
        }
        
        // Validate password
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            Map<String, Object> error = Map.of("error", "Password is required");
            return ResponseEntity.badRequest().body(error);
        }
        
        Map<String, Object> result = userService.loginUser(request.getUsername(), request.getPassword());
        
        if (result.containsKey("error")) {
            return ResponseEntity.status(401).body(result);
        }
        
        return ResponseEntity.ok(result);
    }

}
