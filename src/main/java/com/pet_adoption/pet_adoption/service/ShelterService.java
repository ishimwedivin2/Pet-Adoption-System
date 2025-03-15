package com.pet_adoption.pet_adoption.service;


import com.pet_adoption.pet_adoption.model.Shelter;
import com.pet_adoption.pet_adoption.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {
    @Autowired
    private ShelterRepository shelterRepository;

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }
}
