package com.pet_adoption.pet_adoption.controller;


import com.pet_adoption.pet_adoption.model.Donation;
import com.pet_adoption.pet_adoption.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/donations")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Donation> getDonationsByUser(@PathVariable Long userId) {
        return donationService.getDonationsByUserId(userId);
    }

    @PostMapping
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}
