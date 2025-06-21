package com.veterinary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineResponseDTO {

    private Long id;

    private String name;

    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    private Long animalId;
}
