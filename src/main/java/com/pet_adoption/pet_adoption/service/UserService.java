package com.pet_adoption.pet_adoption.service;

import com.pet_adoption.pet_adoption.model.ResetToken;
import com.pet_adoption.pet_adoption.model.User;
import com.pet_adoption.pet_adoption.repository.ResetTokenRepository;
import com.pet_adoption.pet_adoption.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.PostConstruct;  // Or javax.annotation.PostConstruct depending on Spring version
import com.pet_adoption.pet_adoption.model.Role;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Autowired
    private EmailService emailService;

    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();

    // CRUD
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword()); // No encoding
            user.setRole(userDetails.getRole());
            user.setPhone(userDetails.getPhone());
            user.setAddress(userDetails.getAddress());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    public Optional<User> login(String username, String password) {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isPresent() && user.get().getPassword().equals(password)) {
//            return user;
//        }
//        return Optional.empty();
//    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // Step 1: Generate OTP
            String otp = String.format("%06d", (int)(Math.random() * 1000000));

            // Step 2: Store OTP temporarily
            otpStorage.put(username, otp);

            // Step 3: Send OTP to user email
            String subject = "Your Pet Adoption 2FA Code";
            String message = "Hi " + username + ",\n\nYour 2FA code is: " + otp + "\n\nExpires in 5 minutes.";
            emailService.sendEmail(user.get().getEmail(), subject, message);

            return user; // Authentication succeeded, pending OTP verification
        }
        return Optional.empty();
    }

    public boolean verifyOtp(String username, String otp) {
        String storedOtp = otpStorage.get(username);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(username); // One-time use
            return true;
        }
        return false;
    }





    // === PASSWORD RESET FLOW ===

    public void createPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        ResetToken resetToken = new ResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
        resetTokenRepository.save(resetToken);
        sendPasswordResetEmail(user, token);
    }

    public void sendPasswordResetEmail(User user, String token) {
        String resetUrl = "http://localhost:8080/reset-password?token=" + token;
        String subject = "Reset Your Password";
        String message = "Hi " + user.getUsername() + ",\n\n"
                + "Click the link below to reset your password:\n"
                + resetUrl + "\n\n"
                + "This link will expire in 30 minutes.";
        emailService.sendEmail(user.getEmail(), subject, message);
    }

    public boolean isResetTokenValid(String token) {
        Optional<ResetToken> resetTokenOpt = resetTokenRepository.findByToken(token);
        return resetTokenOpt.isPresent() && resetTokenOpt.get().getExpiryDate().isAfter(LocalDateTime.now());
    }

    public boolean resetPassword(String token, String newPassword) {
        Optional<ResetToken> resetTokenOpt = resetTokenRepository.findByToken(token);
        if (resetTokenOpt.isPresent()) {
            ResetToken resetToken = resetTokenOpt.get();
            if (resetToken.getExpiryDate().isAfter(LocalDateTime.now())) {
                User user = resetToken.getUser();
                user.setPassword(newPassword); // Plain password
                userRepository.save(user);
                resetTokenRepository.delete(resetToken); // Invalidate token
                return true;
            }
        }
        return false;
    }

    @Scheduled(cron = "0 0 * * * *") // Clean expired tokens every hour
    public void cleanUpExpiredTokens() {
        List<ResetToken> tokens = resetTokenRepository.findAll();
        for (ResetToken token : tokens) {
            if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
                resetTokenRepository.delete(token);
            }
        }
    }


    @PostConstruct
    public void createAdminIfNotExists() {
        String adminUsername = "admin";
        Optional<User> adminOpt = userRepository.findByUsername(adminUsername);

        if (adminOpt.isEmpty()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setEmail("admin@petadoption.com");
            admin.setPassword("admin123");  // Remember to encode this later
            admin.setRole(Role.ADMIN);      // <-- use enum here
            admin.setPhone("0000000000");
            admin.setAddress("Admin Address");

            userRepository.save(admin);
            System.out.println("Admin user created");
        } else {
            System.out.println("Admin user already exists");
        }
    }

}
