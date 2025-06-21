package com.veterinary.business;

import com.veterinary.exception.BusinessException;
import com.veterinary.repository.AnimalVaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AnimalVaccineBusinessRules {

    private final AnimalVaccineRepository animalVaccineRepository;

    public void checkIfActiveVaccineExists(Long animalId, String vaccineCode) {
        boolean exists = animalVaccineRepository.existsByAnimalIdAndVaccineCodeAndProtectionFinishDateAfter(
                animalId, vaccineCode, LocalDate.now()
        );
        if (exists) {
            throw new BusinessException("Bu hayvan için aynı kodlu ve aktif koruyuculuk süresi devam eden aşı zaten mevcut.");
        }
    }
}
