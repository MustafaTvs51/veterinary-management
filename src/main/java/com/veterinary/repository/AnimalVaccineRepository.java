package com.veterinary.repository;

import com.veterinary.model.AnimalVaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AnimalVaccineRepository extends JpaRepository<AnimalVaccine, Long> {

    List<AnimalVaccine> findByAnimalId(Long animalId);

    // Aynı hayvan için, aynı aşı koduna ait ve protectionFinishDate > now olan kayıt var mı?
    boolean existsByAnimalIdAndVaccineCodeAndProtectionFinishDateAfter(Long animalId, String vaccineCode, LocalDate date);

    // Koruyuculuk bitiş tarihine göre filtreleme
    List<AnimalVaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
