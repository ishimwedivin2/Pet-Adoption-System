package com.pet_adoption.pet_adoption.controller;


import com.pet_adoption.pet_adoption.model.Shelter;
import com.pet_adoption.pet_adoption.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shelters")
public class ShelterController {
    @Autowired
    private ShelterService shelterService;

    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        return shelterService.getShelterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Shelter createShelter(@RequestBody Shelter shelter) {
        return shelterService.createShelter(shelter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.noContent().build();
    }
}

