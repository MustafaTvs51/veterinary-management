package com.veterinary.repository;

import com.veterinary.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime start, LocalDateTime end);

}
