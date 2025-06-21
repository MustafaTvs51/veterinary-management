package com.veterinary.service;

import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;

import java.util.List;

public interface VaccineService {

    VaccineResponseDTO save(VaccineRequestDTO dto);

    List<VaccineResponseDTO> getAll();

    VaccineResponseDTO getById(Long id);

    void delete(Long id);
}
