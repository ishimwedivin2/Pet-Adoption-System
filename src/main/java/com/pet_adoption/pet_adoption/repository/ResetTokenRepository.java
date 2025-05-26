package com.pet_adoption.pet_adoption.repository;



import com.pet_adoption.pet_adoption.model.ResetToken;
import com.pet_adoption.pet_adoption.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    void deleteByToken(String token);
    Optional<ResetToken> findByUser(User user);
    Optional<ResetToken> findByToken(String token);
}