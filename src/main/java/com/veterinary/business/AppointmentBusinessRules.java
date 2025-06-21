package com.veterinary.business;

import com.veterinary.exception.AppointmentConflictException;
import com.veterinary.repository.AppointmentRepository;
import com.veterinary.repository.AvailableDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppointmentBusinessRules {

    private final AvailableDateRepository availableDateRepository;
    private final AppointmentRepository appointmentRepository;

    public void checkDoctorAvailability(Long doctorId, LocalDateTime appointmentDateTime) {
        LocalDate appointmentDate = appointmentDateTime.toLocalDate();

        boolean isAvailableDay = availableDateRepository.existsByDoctorIdAndAvailableDate(doctorId, appointmentDate);

        if (!isAvailableDay) {
            throw new AppointmentConflictException("Doktor bu tarihte çalışmamaktadır!");
        }

        LocalDateTime startOfHour = appointmentDateTime.withMinute(0).withSecond(0).withNano(0);

        boolean hasConflict = appointmentRepository.existsByDoctorIdAndAppointmentDate(doctorId, startOfHour);

        if (hasConflict) {
            throw new AppointmentConflictException("Girilen saatte başka bir randevu mevcuttur.");
        }
    }
}
