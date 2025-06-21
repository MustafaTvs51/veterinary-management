package com.veterinary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateResponseDTO {

    private Long id;

    private Long doctorId;

    private LocalDate availableDate;
}
