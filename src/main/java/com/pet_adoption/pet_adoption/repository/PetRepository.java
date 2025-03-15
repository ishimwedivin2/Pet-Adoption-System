package com.pet_adoption.pet_adoption.repository;


import com.pet_adoption.pet_adoption.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(String status);
    List<Pet> findByShelterId(Long shelterId);
}
