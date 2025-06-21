package com.veterinary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalVaccineResponseDTO {

    private Long id;

    private Long animalId;

    private Long vaccineId;

    private LocalDate applicationDate;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;
}
