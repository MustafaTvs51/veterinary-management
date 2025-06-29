package com.veterinary.mapper;

import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;
import com.veterinary.model.Animal;
import com.veterinary.model.Vaccine;

public class VaccineMapper {
    public static Vaccine toEntity(VaccineRequestDTO dto, Animal animal) {
        return Vaccine.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .protectionStartDate(dto.getProtectionStartDate())
                .protectionFinishDate(dto.getProtectionFinishDate())
                .animal(animal)
                .build();
    }

    public static VaccineResponseDTO toDTO(Vaccine vaccine) {
        return VaccineResponseDTO.builder()
                .id(vaccine.getId())
                .name(vaccine.getName())
                .code(vaccine.getCode())
                .protectionStartDate(vaccine.getProtectionStartDate())
                .protectionFinishDate(vaccine.getProtectionFinishDate())
                .animalId(vaccine.getAnimal().getId())
                .build();
    }
}
