package com.veterinary.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateRequestDTO {

    @NotNull
    private Long doctorId;

    @NotNull
    private LocalDate availableDate;
}
