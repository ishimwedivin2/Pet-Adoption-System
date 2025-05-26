package com.pet_adoption.pet_adoption.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String message) {
        // Simulate email sending
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message:\n" + message);
    }
}
