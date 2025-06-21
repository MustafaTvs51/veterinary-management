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

    // Aynı isim, kod ve hayvan için koruma bitiş tarihi geçmemiş aşı kontrolü
    public void checkIfVaccineAlreadyExistsForAnimal(String name, String code, Long animalId) {
        boolean exists = vaccineRepository.existsByNameIgnoreCaseAndCodeIgnoreCaseAndAnimalIdAndProtectionFinishDateAfter(
                name, code, animalId, LocalDate.now()
        );
        if (exists) {
            throw new BusinessException("Bu hayvana aynı isimde ve kodda, koruma süresi devam eden aşı zaten uygulanmış.");
        }
    }

    // Tarih tutarlılığı kontrolü
    public void validateProtectionDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new BusinessException("Koruma bitiş tarihi, başlangıç tarihinden önce olamaz.");
        }
    }
}
