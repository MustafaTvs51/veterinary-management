package com.veterinary.service;

import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {

    VaccineResponseDTO save(VaccineRequestDTO dto);

    List<VaccineResponseDTO> getAll();

    VaccineResponseDTO getById(Long id);

    void delete(Long id);

    List<VaccineResponseDTO> getByAnimalId(Long animalId);

    List<VaccineResponseDTO> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    VaccineResponseDTO update(Long id, VaccineRequestDTO dto);

}
