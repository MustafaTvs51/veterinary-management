package com.veterinary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.veterinary.model.Animal;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    boolean existsByOwnerId(Long ownerId);
    boolean existsByNameIgnoreCaseAndOwnerId(String name, Long ownerId);

    boolean existsByNameAndOwnerIdAndIdNot(String name, Long ownerId, Long animalId);

    List<Animal> findByNameContainingIgnoreCase(String name);
}
