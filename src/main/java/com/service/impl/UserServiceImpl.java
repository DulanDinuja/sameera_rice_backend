package com.service.impl;

import com.domain.PendingRegistration;
import com.domain.User;
import com.repository.PendingRegistrationRepository;
import com.repository.UserRepository;
import com.resourse.RegistrationRequest;
import com.service.EmailService;
import com.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PendingRegistrationRepository pendingRegistrationRepository;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public String registerUser(RegistrationRequest request) {
        if (request.getPassword() == null || request.getConfirmPassword() == null)
            return "Password fields cannot be empty";

        if (!request.getPassword().equals(request.getConfirmPassword()))
            return "Passwords do not match";

        // Check if username, email, or NIC already exists in users table
        if (userRepository.existsByUsername(request.getUsername()))
            return "Username already exists";

        if (userRepository.existsByEmail(request.getEmail()))
            return "Email already exists";

        if (userRepository.existsByNic(request.getNic()))
            return "NIC already exists";

        // Check if there's an existing pending registration and delete it (for re-registration)
        PendingRegistration existingPending = pendingRegistrationRepository.findByEmail(request.getEmail());
        if (existingPending != null) {
            pendingRegistrationRepository.delete(existingPending);
        }

        // Also check pending registrations for username/nic conflicts
        if (pendingRegistrationRepository.existsByUsername(request.getUsername()))
            return "Username already has a pending registration";

        if (pendingRegistrationRepository.existsByNic(request.getNic()))
            return "NIC already has a pending registration";

        String code = String.format("%06d", new Random().nextInt(999999));

        // Store in pending registrations table (NOT in users table)
        PendingRegistration pendingRegistration = new PendingRegistration();
        pendingRegistration.setUsername(request.getUsername());
        pendingRegistration.setEmail(request.getEmail());
        pendingRegistration.setMobileNumber(request.getMobileNumber());
        pendingRegistration.setNic(request.getNic());
        pendingRegistration.setPassword(passwordEncoder.encode(request.getPassword()));
        pendingRegistration.setVerificationCode(code);
        pendingRegistration.setVerificationExpiry(LocalDateTime.now().plusMinutes(2));

        pendingRegistrationRepository.save(pendingRegistration);

        // Try to send verification email
        try {
            emailService.sendVerificationCode(request.getEmail(), code);
        } catch (Exception e) {
            // If email fails, delete the pending registration and return error
            pendingRegistrationRepository.delete(pendingRegistration);
            return "Failed to send verification email. Please check your email address or try again later.";
        }

        return "Verification code sent to " + request.getEmail() + ". Please verify within 2 minutes.";
    }

    @Override
    @Transactional
    public String verifyRegistration(String email, String code) {
        // Look for pending registration
        PendingRegistration pendingRegistration = pendingRegistrationRepository.findByEmail(email);

        if (pendingRegistration == null) {
            // Check if user is already verified
            User existingUser = userRepository.findByEmail(email);
            if (existingUser != null && existingUser.isApproved()) {
                return "Account already verified";
            }
            return "No pending registration found for this email";
        }

        if (pendingRegistration.getVerificationCode() == null ||
            !pendingRegistration.getVerificationCode().equals(code)) {
            return "Invalid verification code";
        }

        if (LocalDateTime.now().isAfter(pendingRegistration.getVerificationExpiry())) {
            // Delete expired pending registration
            pendingRegistrationRepository.delete(pendingRegistration);
            return "Verification code has expired. Please register again.";
        }

        // Verification successful - now insert user into users table
        User user = new User();
        user.setUsername(pendingRegistration.getUsername());
        user.setEmail(pendingRegistration.getEmail());
        user.setMobileNumber(pendingRegistration.getMobileNumber());
        user.setNic(pendingRegistration.getNic());
        user.setPassword(pendingRegistration.getPassword());
        user.setApproved(true);  // User is verified
        user.setVerificationCode(null);
        user.setVerificationExpiry(null);

        userRepository.save(user);

        // Delete the pending registration
        pendingRegistrationRepository.delete(pendingRegistration);

        return "Account verified successfully";
    }

    @Override
    public Map<String, Object> loginUser(String username, String password) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            response.put("error", "User not found");
            return response;
        }

        // No need to check isApproved - users in the users table are already verified
        // Email verification is only required during registration

        if (passwordEncoder.matches(password, user.getPassword())) {
            response.put("access_token", UUID.randomUUID().toString());
            response.put("user", getUserDetails(username));
        } else {
            response.put("error", "Invalid credentials");
        }
        return response;
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
