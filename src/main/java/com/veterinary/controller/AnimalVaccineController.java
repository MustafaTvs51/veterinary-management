package com.veterinary.controller;

import com.veterinary.dto.AnimalVaccineRequestDTO;
import com.veterinary.dto.AnimalVaccineResponseDTO;
import com.veterinary.service.AnimalVaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/animal-vaccines")
@Tag(name = "Animal Vaccine Controller", description = "Hayvan aşıları için API")
@RequiredArgsConstructor
public class AnimalVaccineController {

    private final AnimalVaccineService animalVaccineService;

    @Operation(summary = "Yeni aşı kaydı oluşturur")
    @PostMapping
    public ResponseEntity<AnimalVaccineResponseDTO> save(@Valid @RequestBody AnimalVaccineRequestDTO dto) {
        AnimalVaccineResponseDTO saved = animalVaccineService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Hayvanın tüm aşılarını getirir")
    @GetMapping("/by-animal/{animalId}")
    public ResponseEntity<List<AnimalVaccineResponseDTO>> getByAnimalId(@PathVariable Long animalId) {
        return ResponseEntity.ok(animalVaccineService.getByAnimalId(animalId));
    }

    @Operation(summary = "Koruyuculuğu belirli tarihler arasında biten aşıları listeler")
    @GetMapping("/protection-finish")
    public ResponseEntity<List<AnimalVaccineResponseDTO>> getByProtectionFinishDateBetween(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(animalVaccineService.getByProtectionFinishDateBetween(start, end));
    }
}
