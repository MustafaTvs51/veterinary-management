package com.veterinary.repository;

import com.veterinary.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    boolean existsByCode(String code);
}
