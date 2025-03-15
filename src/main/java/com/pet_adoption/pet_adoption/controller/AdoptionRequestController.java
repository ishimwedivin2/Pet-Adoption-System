package com.pet_adoption.pet_adoption.controller;


import com.pet_adoption.pet_adoption.model.AdoptionRequest;
import com.pet_adoption.pet_adoption.service.AdoptionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adoptions")
public class AdoptionRequestController {
    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @GetMapping
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequest> getRequestById(@PathVariable Long id) {
        return adoptionRequestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdoptionRequest createRequest(@RequestBody AdoptionRequest request) {
        return adoptionRequestService.createRequest(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        adoptionRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
