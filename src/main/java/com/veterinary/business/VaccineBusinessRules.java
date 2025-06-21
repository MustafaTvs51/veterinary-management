package com.veterinary.business;

import com.veterinary.exception.BusinessException;
import com.veterinary.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VaccineBusinessRules {

    private final VaccineRepository vaccineRepository;

    public void checkIfVaccineAlreadyExistsForAnimal(String name, Long animalId) {
        boolean exists = vaccineRepository.existsByNameIgnoreCaseAndAnimalId(name, animalId);
        if (exists) {
            throw new BusinessException("Bu hayvana aynı isimde aşı zaten uygulanmış.");
        }
    }

    public void validateProtectionDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new BusinessException("Koruma bitiş tarihi, başlangıç tarihinden önce olamaz.");
        }
    }
}
