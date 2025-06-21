package com.veterinary.mapper;

import com.veterinary.dto.AnimalRequestDTO;
import com.veterinary.dto.AnimalResponseDTO;
import com.veterinary.model.Animal;
import com.veterinary.model.Customer;

public class AnimalMapper {

    public static Animal toEntity(AnimalRequestDTO dto, Customer owner) {
        if (dto == null) return null;
        Animal animal = new Animal();
        animal.setName(dto.getName());
        animal.setSpecies(dto.getSpecies());
        animal.setBreed(dto.getBreed());
        animal.setOwner(owner);
        // Diğer alanlar varsa buraya ekle
        return animal;
    }

    public static AnimalResponseDTO toDTO(Animal animal) {
        if (animal == null) return null;
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSpecies(animal.getSpecies());
        dto.setBreed(animal.getBreed());
        dto.setOwnerId(animal.getOwner() != null ? animal.getOwner().getId() : null);
        // Diğer alanlar varsa buraya ekle
        return dto;
    }
}
