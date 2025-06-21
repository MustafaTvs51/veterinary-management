package com.veterinary.mapper;

import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;
import com.veterinary.model.Animal;
import com.veterinary.model.Vaccine;

public class VaccineMapper {

    public static Vaccine toEntity(VaccineRequestDTO dto, Animal animal) {
        if (dto == null || animal == null) return null;

        return Vaccine.builder()
                .name(dto.getName())
                .protectionStartDate(dto.getProtectionStartDate())
                .protectionEndDate(dto.getProtectionEndDate())
                .animal(animal)
                .build();
    }

    public static VaccineResponseDTO toDTO(Vaccine vaccine) {
        if (vaccine == null) return null;

        VaccineResponseDTO dto = new VaccineResponseDTO();
        dto.setId(vaccine.getId());
        dto.setName(vaccine.getName());
        dto.setProtectionStartDate(vaccine.getProtectionStartDate());
        dto.setProtectionEndDate(vaccine.getProtectionEndDate());
        dto.setAnimalId(vaccine.getAnimal().getId());

        return dto;
    }
}
