package com.controller;

import com.resourse.LoginRequest;
import com.resourse.RegistrationRequest;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        String result = userService.registerUser(request);
        if (result.equals("User registered successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = userService.loginUser(request.getUsername(), request.getPassword());
        if (result.equals("Login successful")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(401).body(result);
    }

}
