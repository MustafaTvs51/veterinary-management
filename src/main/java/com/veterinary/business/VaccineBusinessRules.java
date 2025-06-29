package com.veterinary.business;

import com.veterinary.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VaccineBusinessRules {

    // Tarih tutarlılığı kontrolü
    public void validateProtectionDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new BusinessException("Koruma bitiş tarihi, başlangıç tarihinden önce olamaz.");
        }
    }
}
