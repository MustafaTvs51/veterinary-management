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

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineBusinessRules vaccineBusinessRules;
    private final AnimalRepository animalRepository;

    @Override
    public VaccineResponseDTO save(VaccineRequestDTO dto) {
        vaccineBusinessRules.validateProtectionDates(dto.getProtectionStartDate(), dto.getProtectionFinishDate());

        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hayvan bulunamadı: " + dto.getAnimalId()));

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
        if (!vaccineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aşı bulunamadı: " + id);
        }

        vaccineRepository.deleteById(id);
    }

    @Override
    public List<VaccineResponseDTO> getByAnimalId(Long animalId) {
        return vaccineRepository.findByAnimal_Id(animalId).stream()
                .map(VaccineMapper::toDTO)
                .toList();
    }


    @Override
    public List<VaccineResponseDTO> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate).stream()
                .map(VaccineMapper::toDTO)
                .toList();
    }

    @Override
    public VaccineResponseDTO update(Long id, VaccineRequestDTO dto) {
        Vaccine existing = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aşı bulunamadı: " + id));

        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hayvan bulunamadı: " + dto.getAnimalId()));

        vaccineBusinessRules.validateProtectionDates(dto.getProtectionStartDate(), dto.getProtectionFinishDate());

        existing.setName(dto.getName());
        existing.setCode(dto.getCode());
        existing.setProtectionStartDate(dto.getProtectionStartDate());
        existing.setProtectionFinishDate(dto.getProtectionFinishDate());
        existing.setAnimal(animal);

        Vaccine updated = vaccineRepository.save(existing);
        return VaccineMapper.toDTO(updated);
    }
}
