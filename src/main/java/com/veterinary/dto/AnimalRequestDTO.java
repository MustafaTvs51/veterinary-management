package com.veterinary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimalRequestDTO {

    @NotBlank(message = "Hayvan ismi boş olamaz")
    private String name;

    @NotBlank(message = "Tür boş olamaz")
    private String species;

    private String breed;

    @NotNull(message = "Sahip ID'si boş olamaz")
    private Long ownerId;
}
