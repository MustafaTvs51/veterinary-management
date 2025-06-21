package com.veterinary.repository;

import com.veterinary.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    boolean existsByNameIgnoreCaseAndOwnerId(String name, Long ownerId);
}
