package com.veterinary.service;

import com.veterinary.dto.AvailableDateRequestDTO;
import com.veterinary.dto.AvailableDateResponseDTO;
import com.veterinary.model.AvailableDate;

import java.util.List;

public interface AvailableDateService {

    AvailableDateResponseDTO save(AvailableDateRequestDTO dto);

    List<AvailableDateResponseDTO> getAll();

    void delete(Long id);

    List<AvailableDateResponseDTO> getAvailableDatesForDoctor(Long doctorId);

    AvailableDate getById(Long id);

    AvailableDateResponseDTO update(Long id, AvailableDateRequestDTO dto);


}
