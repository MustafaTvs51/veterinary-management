package com.veterinary.service.impl;

import com.veterinary.business.VaccineBusinessRules;
import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;
import com.veterinary.exception.ResourceNotFoundException;
import com.veterinary.mapper.VaccineMapper;
import com.veterinary.model.Animal;
import com.veterinary.model.Vaccine;
import com.veterinary.repository.AnimalRepository;
import com.veterinary.repository.VaccineRepository;
import com.veterinary.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineBusinessRules vaccineBusinessRules;
    private final AnimalRepository animalRepository; // AnimalRepository eklendi

    @Override
    public VaccineResponseDTO save(VaccineRequestDTO dto) {
        // İş kurallarını uygula
        vaccineBusinessRules.checkIfVaccineAlreadyExistsForAnimal(dto.getName(), dto.getAnimalId());
        vaccineBusinessRules.validateProtectionDates(dto.getProtectionStartDate(), dto.getProtectionEndDate());

        // Animal nesnesini veritabanından al
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hayvan bulunamadı: " + dto.getAnimalId()));

        // Mapper'a dto ve animal ile dönüşüm yaptır
        Vaccine vaccine = VaccineMapper.toEntity(dto, animal);
        Vaccine savedVaccine = vaccineRepository.save(vaccine);

        return VaccineMapper.toDTO(savedVaccine);
    }

    @Override
    public List<VaccineResponseDTO> getAll() {
        return vaccineRepository.findAll().stream()
                .map(VaccineMapper::toDTO)
                .toList();
    }

    @Override
    public VaccineResponseDTO getById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aşı bulunamadı: " + id));
        return VaccineMapper.toDTO(vaccine);
    }

    @Override
    public void delete(Long id) {
        vaccineRepository.deleteById(id);
    }
}
