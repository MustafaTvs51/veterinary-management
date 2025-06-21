package com.veterinary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.veterinary.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    boolean existsByOwnerId(Long ownerId);
    boolean existsByNameIgnoreCaseAndOwnerId(String name, Long ownerId);
}
