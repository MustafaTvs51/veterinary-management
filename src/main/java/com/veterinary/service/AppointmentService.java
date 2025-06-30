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
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.veterinary.model.Appointment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


    public List<AppointmentResponseDTO> getByDoctorIdAndDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, startOfDay, endOfDay)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponseDTO> getByAnimalIdAndDate(Long animalId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, startOfDay, endOfDay)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private AppointmentResponseDTO convertToDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(appointment.getId());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setAnimalId(appointment.getAnimal().getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        return dto;
    }
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " id'li randevu bulunamadı."));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));

        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));


        appointmentBusinessRules.checkDoctorAvailability(doctor.getId(), dto.getAppointmentDate());


        existing.setDoctor(doctor);
        existing.setAnimal(animal);
        existing.setAppointmentDate(dto.getAppointmentDate());

        Appointment updated = appointmentRepository.save(existing);

        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(updated.getId());
        responseDTO.setDoctorId(updated.getDoctor().getId());
        responseDTO.setAnimalId(updated.getAnimal().getId());
        responseDTO.setAppointmentDate(updated.getAppointmentDate());

        return responseDTO;
    }


}
