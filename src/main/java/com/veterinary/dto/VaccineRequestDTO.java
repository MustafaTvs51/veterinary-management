package com.veterinary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotNull
    private LocalDate protectionStartDate;

    @NotNull
    private LocalDate protectionFinishDate;

    @NotNull
    private Long animalId;
}
