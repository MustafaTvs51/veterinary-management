package com.veterinary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AnimalRequestDTO {

    @NotBlank(message = "Hayvan ismi boş olamaz")
    private String name;

    @NotBlank(message = "Tür boş olamaz")
    private String species;

    private String breed;

    @NotBlank(message = "Cinsiyet boş olamaz")
    private String gender;

    private String colour;

    private LocalDate dateOfBirth;

    @NotNull(message = "Sahip ID'si boş olamaz")
    private Long ownerId;
}
