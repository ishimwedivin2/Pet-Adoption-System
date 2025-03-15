package com.pet_adoption.pet_adoption.repository;



import com.pet_adoption.pet_adoption.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByShelterId(Long shelterId);
}
