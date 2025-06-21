package com.veterinary.repository;

import com.veterinary.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Doktor ve tarih-saat bazında randevu çakışması kontrolü
    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    // İstenirse tarih aralığında randevu sorgusu için örnek (isteğe bağlı)
    // List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

}
