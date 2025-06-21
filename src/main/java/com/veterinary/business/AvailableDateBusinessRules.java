package com.veterinary.business;

import com.veterinary.exception.BusinessException;
import com.veterinary.repository.AvailableDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AvailableDateBusinessRules {

    private final AvailableDateRepository availableDateRepository;

    public void checkIfDateAlreadyExistsForDoctor(Long doctorId, LocalDate date) {
        boolean exists = availableDateRepository.existsByDoctorIdAndAvailableDate(doctorId, date);
        if (exists) {
            throw new BusinessException("Doktor için bu gün zaten kayıtlı müsait gün mevcut: " + date);
        }
    }
}
