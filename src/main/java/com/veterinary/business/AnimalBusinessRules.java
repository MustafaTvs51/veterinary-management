package com.veterinary.business;

import com.veterinary.exception.BusinessException;
import com.veterinary.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimalBusinessRules {

    private final AnimalRepository animalRepository;

    public void checkIfAnimalNameExistsForOwner(String name, Long ownerId) {
        boolean exists = animalRepository.existsByNameIgnoreCaseAndOwnerId(name, ownerId);
        if (exists) {
            throw new BusinessException("Bu isimde aynı sahip için bir hayvan zaten mevcut: " + name);
        }
    }
}
