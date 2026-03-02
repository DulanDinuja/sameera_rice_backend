package com.service;

public interface EmailService {
    void sendVerificationCode(String toEmail, String code);
}
