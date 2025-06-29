package com.veterinary.service;

import com.veterinary.business.AnimalVaccineBusinessRules;
import com.veterinary.dto.AnimalVaccineRequestDTO;
import com.veterinary.dto.AnimalVaccineResponseDTO;
import com.veterinary.model.Animal;
import com.veterinary.model.AnimalVaccine;
import com.veterinary.model.Vaccine;
import com.veterinary.repository.AnimalRepository;
import com.veterinary.repository.AnimalVaccineRepository;
import com.veterinary.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalVaccineService {

    private final AnimalVaccineRepository animalVaccineRepository;
    private final AnimalRepository animalRepository;
    private final VaccineRepository vaccineRepository;
    private final AnimalVaccineBusinessRules businessRules;

    public AnimalVaccineResponseDTO save(AnimalVaccineRequestDTO dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));

        Vaccine vaccine = vaccineRepository.findById(dto.getVaccineId())
                .orElseThrow(() -> new RuntimeException("Aşı bulunamadı"));

        // Aynı kodlu aktif aşı varsa engelle
        businessRules.checkIfActiveVaccineExists(animal.getId(), vaccine.getCode());

        AnimalVaccine animalVaccine = new AnimalVaccine();
        animalVaccine.setAnimal(animal);
        animalVaccine.setVaccine(vaccine);
        animalVaccine.setApplicationDate(dto.getApplicationDate());
        animalVaccine.setProtectionStartDate(dto.getProtectionStartDate());
        animalVaccine.setProtectionFinishDate(dto.getProtectionFinishDate());

        animalVaccine = animalVaccineRepository.save(animalVaccine);

        return mapToDto(animalVaccine);
    }

    public List<AnimalVaccineResponseDTO> getByAnimalId(Long animalId) {
        return animalVaccineRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<AnimalVaccineResponseDTO> getByProtectionFinishDateBetween(LocalDate start, LocalDate end) {
        return animalVaccineRepository.findByProtectionFinishDateBetween(start, end)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AnimalVaccineResponseDTO mapToDto(AnimalVaccine av) {
        AnimalVaccineResponseDTO dto = new AnimalVaccineResponseDTO();
        dto.setId(av.getId());
        dto.setAnimalId(av.getAnimal().getId());
        dto.setVaccineId(av.getVaccine().getId());
        dto.setApplicationDate(av.getApplicationDate());
        dto.setProtectionStartDate(av.getProtectionStartDate());
        dto.setProtectionFinishDate(av.getProtectionFinishDate());
        return dto;
    }
}
