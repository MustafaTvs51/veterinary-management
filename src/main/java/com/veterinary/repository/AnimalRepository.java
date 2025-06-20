package com.veterinary.repository;

import com.veterinary.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    boolean existsByCustomerId(Long customerId);
}
