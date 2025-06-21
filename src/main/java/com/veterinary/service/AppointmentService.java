package com.veterinary.service;

import com.veterinary.business.AppointmentBusinessRules;
import com.veterinary.dto.AppointmentRequestDTO;
import com.veterinary.dto.AppointmentResponseDTO;
import com.veterinary.model.Appointment;
import com.veterinary.model.Animal;
import com.veterinary.model.Doctor;
import com.veterinary.repository.AppointmentRepository;
import com.veterinary.repository.AnimalRepository;
import com.veterinary.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentBusinessRules appointmentBusinessRules;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    public AppointmentResponseDTO save(AppointmentRequestDTO dto) {
        // Doktor ve hayvan bulunmalı
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));

        // Randevu uygunluğunu kontrol et
        appointmentBusinessRules.checkDoctorAvailability(doctor.getId(), dto.getAppointmentDate());

        // Randevu oluştur
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        appointment.setAppointmentDate(dto.getAppointmentDate());

        appointment = appointmentRepository.save(appointment);

        // Response DTO'ya dönüştür
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(appointment.getId());
        responseDTO.setDoctorId(doctor.getId());
        responseDTO.setAnimalId(animal.getId());
        responseDTO.setAppointmentDate(appointment.getAppointmentDate());

        return responseDTO;
    }

    public List<AppointmentResponseDTO> getAll() {
        return appointmentRepository.findAll().stream().map(appointment -> {
            AppointmentResponseDTO dto = new AppointmentResponseDTO();
            dto.setId(appointment.getId());
            dto.setDoctorId(appointment.getDoctor().getId());
            dto.setAnimalId(appointment.getAnimal().getId());
            dto.setAppointmentDate(appointment.getAppointmentDate());
            return dto;
        }).collect(Collectors.toList());
    }

    public AppointmentResponseDTO getById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " id'li randevu bulunamadı."));
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(appointment.getId());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setAnimalId(appointment.getAnimal().getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        return dto;
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException(id + " id'li randevu bulunamadı.");
        }
        appointmentRepository.deleteById(id);
    }

    // Tarih aralığı ve doktora göre filtreleme örneği (isteğe bağlı)
    // public List<AppointmentResponseDTO> findByDoctorAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) { ... }

    // Tarih aralığı ve hayvana göre filtreleme örneği (isteğe bağlı)
    // public List<AppointmentResponseDTO> findByAnimalAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) { ... }
}
