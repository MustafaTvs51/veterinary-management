package com.veterinary.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRequestDTO {

    @NotBlank(message = "Aşı adı boş olamaz")
    private String name;

    @NotNull(message = "Koruma başlangıç tarihi zorunludur")
    private LocalDate protectionStartDate;

    @NotNull(message = "Koruma bitiş tarihi zorunludur")
    private LocalDate protectionEndDate;

    @NotNull(message = "Hayvan ID'si zorunludur")
    private Long animalId;
}
