package com.pet_adoption.pet_adoption.controller;


import com.pet_adoption.pet_adoption.model.User;
import com.pet_adoption.pet_adoption.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        Optional<User> user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
//        if (user.isPresent()) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String username, @RequestBody String password) {
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

}
