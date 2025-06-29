package com.veterinary.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AnimalResponseDTO {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Long ownerId;
}
