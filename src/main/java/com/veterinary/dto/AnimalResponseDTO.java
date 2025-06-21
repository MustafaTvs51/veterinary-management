package com.veterinary.dto;

import lombok.Data;

@Data
public class AnimalResponseDTO {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private Long ownerId;
}
