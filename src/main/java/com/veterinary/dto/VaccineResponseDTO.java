package com.veterinary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineResponseDTO {
    private Long id;
    private String name;
    private LocalDate protectionStartDate;
    private LocalDate protectionEndDate;
    private Long animalId;
}
