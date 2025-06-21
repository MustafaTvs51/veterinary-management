package com.veterinary.mapper;

import com.veterinary.dto.DoctorRequestDTO;
import com.veterinary.dto.DoctorResponseDTO;
import com.veterinary.model.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequestDTO dto) {
        if (dto == null) return null;
        Doctor doctor = new Doctor();
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setPhone(dto.getPhone());
        doctor.setEmail(dto.getEmail());
        return doctor;
    }

    public static DoctorResponseDTO toDTO(Doctor doctor) {
        if (doctor == null) return null;
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setPhone(doctor.getPhone());
        dto.setEmail(doctor.getEmail());
        return dto;
    }
}
