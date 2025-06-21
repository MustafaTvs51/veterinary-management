package com.veterinary.service.impl;

import com.veterinary.business.VaccineBusinessRules;
import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;
import com.veterinary.exception.ResourceNotFoundException;
import com.veterinary.mapper.VaccineMapper;
import com.veterinary.model.Vaccine;
import com.veterinary.repository.VaccineRepository;
import com.veterinary.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineBusinessRules vaccineBusinessRules;

    @Override
    public VaccineResponseDTO save(VaccineRequestDTO dto) {
        // İş kurallarını uygula
        vaccineBusinessRules.checkIfVaccineAlreadyExistsForAnimal(dto.getName(), dto.getAnimalId());
        vaccineBusinessRules.validateProtectionDates(dto.getStartDate(), dto.getEndDate());

        Vaccine vaccine = VaccineMapper.toEntity(dto);
        Vaccine savedVaccine = vaccineRepository.save(vaccine);

        return VaccineMapper.toDTO(savedVaccine);
    }

    // Diğer metotlar burada...
}
