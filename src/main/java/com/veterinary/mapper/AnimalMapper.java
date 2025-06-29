package com.veterinary.mapper;

import com.veterinary.dto.AnimalRequestDTO;
import com.veterinary.dto.AnimalResponseDTO;
import com.veterinary.model.Animal;
import com.veterinary.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public Animal toEntity(AnimalRequestDTO dto, Customer owner) {
        Animal animal = new Animal();
        animal.setName(dto.getName());
        animal.setSpecies(dto.getSpecies());
        animal.setBreed(dto.getBreed());
        animal.setGender(dto.getGender());
        animal.setColour(dto.getColour());
        animal.setDateOfBirth(dto.getDateOfBirth());
        animal.setOwner(owner);
        return animal;
    }

    public AnimalResponseDTO toDTO(Animal animal) {
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSpecies(animal.getSpecies());
        dto.setBreed(animal.getBreed());
        dto.setGender(animal.getGender());
        dto.setColour(animal.getColour());
        dto.setDateOfBirth(animal.getDateOfBirth());
        dto.setOwnerId(animal.getOwner().getId());
        return dto;
    }
}
