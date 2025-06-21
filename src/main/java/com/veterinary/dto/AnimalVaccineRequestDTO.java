package com.veterinary.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalVaccineRequestDTO {

    @NotNull
    private Long animalId;

    @NotNull
    private Long vaccineId;

    @NotNull
    private LocalDate applicationDate;

    @NotNull
    private LocalDate protectionStartDate;

    @NotNull
    private LocalDate protectionFinishDate;
}
