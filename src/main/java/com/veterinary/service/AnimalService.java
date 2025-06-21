package com.veterinary.service;

import com.veterinary.dto.AnimalRequestDTO;
import com.veterinary.dto.AnimalResponseDTO;

import java.util.List;

public interface AnimalService {
    AnimalResponseDTO save(AnimalRequestDTO dto);
    List<AnimalResponseDTO> getAll();
    AnimalResponseDTO getById(Long id);
    void delete(Long id);
}
