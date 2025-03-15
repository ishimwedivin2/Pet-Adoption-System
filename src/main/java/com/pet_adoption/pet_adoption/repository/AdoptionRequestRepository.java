package com.pet_adoption.pet_adoption.repository;



import com.pet_adoption.pet_adoption.model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByUserId(Long userId);
    List<AdoptionRequest> findByPetId(Long petId);
}
