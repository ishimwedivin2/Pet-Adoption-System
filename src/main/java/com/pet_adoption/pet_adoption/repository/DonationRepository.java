package com.pet_adoption.pet_adoption.repository;



import com.pet_adoption.pet_adoption.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserId(Long userId);
}

