package com.veterinary.repository;

import com.veterinary.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    boolean existsByNameIgnoreCaseAndCodeIgnoreCaseAndAnimalIdAndProtectionFinishDateAfter(
            String name, String code, Long animalId, LocalDate date
    );

}
