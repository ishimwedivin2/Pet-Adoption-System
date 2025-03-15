package com.pet_adoption.pet_adoption.service;


import com.pet_adoption.pet_adoption.model.Pet;
import com.pet_adoption.pet_adoption.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> getPetsByStatus(String status) {
        return petRepository.findByStatus(status);
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
