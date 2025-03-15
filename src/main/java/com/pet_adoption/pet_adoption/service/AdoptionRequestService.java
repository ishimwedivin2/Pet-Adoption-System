package com.pet_adoption.pet_adoption.service;


import com.pet_adoption.pet_adoption.model.AdoptionRequest;
import com.pet_adoption.pet_adoption.repository.AdoptionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRequestService {
    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;

    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestRepository.findAll();
    }

    public Optional<AdoptionRequest> getRequestById(Long id) {
        return adoptionRequestRepository.findById(id);
    }

    public List<AdoptionRequest> getRequestsByUserId(Long userId) {
        return adoptionRequestRepository.findByUserId(userId);
    }

    public AdoptionRequest createRequest(AdoptionRequest request) {
        return adoptionRequestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        adoptionRequestRepository.deleteById(id);
    }
}
