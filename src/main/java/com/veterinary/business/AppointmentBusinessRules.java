package com.veterinary.business;

import com.veterinary.exception.AppointmentConflictException;
import com.veterinary.repository.AppointmentRepository;
import com.veterinary.repository.AvailableDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentBusinessRules {

    private final AvailableDateRepository availableDateRepository;
    private final AppointmentRepository appointmentRepository;

    public void checkDoctorAvailability(Long doctorId, LocalDateTime appointmentDateTime) {
        LocalDate appointmentDate = appointmentDateTime.toLocalDate();

        // 1) Doktorun müsait günü var mı? (AvailableDate tablosunda doktorId + appointmentDate)
        boolean isAvailableDay = availableDateRepository.existsByDoctorIdAndAvailableDate(doctorId, appointmentDate);

        if (!isAvailableDay) {
            throw new AppointmentConflictException("Doktor bu tarihte çalışmamaktadır!");
        }

        // 2) Aynı tarihte ve saatte randevu var mı?
        // Burada sadece saat başı randevu olacağından dakikalar 0 olmalı veya aynı saate bakılmalı
        LocalDateTime startOfHour = appointmentDateTime.withMinute(0).withSecond(0).withNano(0);

        boolean hasConflict = appointmentRepository.existsByDoctorIdAndAppointmentDate(startOfHour);

        if (hasConflict) {
            throw new AppointmentConflictException("Girilen saatte başka bir randevu mevcuttur.");
        }
    }
}
