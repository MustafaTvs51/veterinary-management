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

        businessRules.checkIfActiveVaccineExists(animal.getId(), vaccine.getCode());

        AnimalVaccine animalVaccine = new AnimalVaccine();
        animalVaccine.setAnimal(animal);
        animalVaccine.setVaccine(vaccine);
        animalVaccine.setApplicationDate(dto.getApplicationDate());
        animalVaccine.setProtectionStartDate(dto.getProtectionStartDate());
        animalVaccine.setProtectionFinishDate(dto.getProtectionFinishDate());

        animalVaccine = animalVaccineRepository.save(animalVaccine);

        AnimalVaccineResponseDTO responseDTO = new AnimalVaccineResponseDTO();
        responseDTO.setId(animalVaccine.getId());
        responseDTO.setAnimalId(animal.getId());
        responseDTO.setVaccineId(vaccine.getId());
        responseDTO.setApplicationDate(animalVaccine.getApplicationDate());
        responseDTO.setProtectionStartDate(animalVaccine.getProtectionStartDate());
        responseDTO.setProtectionFinishDate(animalVaccine.getProtectionFinishDate());

        return responseDTO;
    }

    public List<AnimalVaccineResponseDTO> getByAnimalId(Long animalId) {
        return animalVaccineRepository.findByAnimalId(animalId)
                .stream()
                .map(av -> {
                    AnimalVaccineResponseDTO dto = new AnimalVaccineResponseDTO();
                    dto.setId(av.getId());
                    dto.setAnimalId(av.getAnimal().getId());
                    dto.setVaccineId(av.getVaccine().getId());
                    dto.setApplicationDate(av.getApplicationDate());
                    dto.setProtectionStartDate(av.getProtectionStartDate());
                    dto.setProtectionFinishDate(av.getProtectionFinishDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Başlangıç ve bitiş tarihleri arasında koruyuculuğu biten aşıları listeleme
    public List<AnimalVaccineResponseDTO> getByProtectionFinishDateBetween(java.time.LocalDate start, java.time.LocalDate end) {
        return animalVaccineRepository.findByProtectionFinishDateBetween(start, end)
                .stream()
                .map(av -> {
                    AnimalVaccineResponseDTO dto = new AnimalVaccineResponseDTO();
                    dto.setId(av.getId());
                    dto.setAnimalId(av.getAnimal().getId());
                    dto.setVaccineId(av.getVaccine().getId());
                    dto.setApplicationDate(av.getApplicationDate());
                    dto.setProtectionStartDate(av.getProtectionStartDate());
                    dto.setProtectionFinishDate(av.getProtectionFinishDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
