package com.pet_adoption.pet_adoption.repository;



import com.pet_adoption.pet_adoption.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
