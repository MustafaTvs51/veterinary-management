package com.veterinary.service.impl;

import com.veterinary.business.AnimalBusinessRules;
import com.veterinary.dto.AnimalRequestDTO;
import com.veterinary.dto.AnimalResponseDTO;
import com.veterinary.exception.ResourceNotFoundException;
import com.veterinary.mapper.AnimalMapper;
import com.veterinary.model.Animal;
import com.veterinary.model.Customer;
import com.veterinary.repository.AnimalRepository;
import com.veterinary.repository.CustomerRepository;
import com.veterinary.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;
    private final AnimalBusinessRules animalBusinessRules;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponseDTO save(AnimalRequestDTO dto) {
        Customer owner = customerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Sahip bulunamadı: " + dto.getOwnerId()));

        animalBusinessRules.checkIfAnimalNameExistsForOwner(dto.getName(), dto.getOwnerId());

        Animal animal = animalMapper.toEntity(dto, owner);
        Animal saved = animalRepository.save(animal);
        return animalMapper.toDTO(saved);
    }

    @Override
    public List<AnimalResponseDTO> getAll() {
        return animalRepository.findAll().stream()
                .map(animalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnimalResponseDTO getById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hayvan bulunamadı: " + id));
        return animalMapper.toDTO(animal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Silinecek hayvan bulunamadı: " + id));
        animalRepository.delete(animal);
    }
    @Override
    public AnimalResponseDTO update(Long id, AnimalRequestDTO dto) {
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hayvan bulunamadı: " + id));

        Customer owner = customerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Sahip bulunamadı: " + dto.getOwnerId()));

        animalBusinessRules.checkIfAnimalNameExistsForOwnerExcludingId(dto.getName(), dto.getOwnerId(), id);

        existingAnimal.setName(dto.getName());
        existingAnimal.setSpecies(dto.getSpecies());
        existingAnimal.setBreed(dto.getBreed());
        existingAnimal.setOwner(owner);

        Animal updated = animalRepository.save(existingAnimal);
        return animalMapper.toDTO(updated);
    }
    @Override
    public List<AnimalResponseDTO> searchByName(String name) {
        List<Animal> animals = animalRepository.findByNameContainingIgnoreCase(name);
        return animals.stream()
                .map(animalMapper::toDTO)
                .collect(Collectors.toList());
    }

}
