package com.veterinary.repository;

import com.veterinary.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimal_Id(Long animalId);


    List<Vaccine> findByProtectionFinishDateBetween(LocalDate start, LocalDate end);

}
