package com.veterinary.service;

import com.veterinary.dto.DoctorRequestDTO;
import com.veterinary.dto.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {

    DoctorResponseDTO save(DoctorRequestDTO dto);

    DoctorResponseDTO getById(Long id);

    List<DoctorResponseDTO> getAll();

    void delete(Long id);
}
