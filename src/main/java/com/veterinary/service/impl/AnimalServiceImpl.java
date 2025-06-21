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

    @Override
    public AnimalResponseDTO save(AnimalRequestDTO dto) {
        // Öncelikle sahibi bul
        Customer owner = customerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Sahip bulunamadı: " + dto.getOwnerId()));

        // İş kuralı kontrolü: Aynı isimli hayvan aynı sahip için olamaz
        animalBusinessRules.checkIfAnimalNameExistsForOwner(dto.getName(), dto.getOwnerId());

        // Mapping ve kayıt
        Animal animal = AnimalMapper.toEntity(dto, owner);
        Animal saved = animalRepository.save(animal);
        return AnimalMapper.toDTO(saved);
    }

    @Override
    public List<AnimalResponseDTO> getAll() {
        return animalRepository.findAll().stream()
                .map(AnimalMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnimalResponseDTO getById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hayvan bulunamadı: " + id));
        return AnimalMapper.toDTO(animal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Silinecek hayvan bulunamadı: " + id));
        animalRepository.delete(animal);
    }
}
