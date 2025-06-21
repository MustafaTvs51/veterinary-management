package com.veterinary.controller;

import com.veterinary.dto.AnimalRequestDTO;
import com.veterinary.dto.AnimalResponseDTO;
import com.veterinary.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@Tag(name = "Animal Controller", description = "Hayvan işlemleri için API")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @Operation(summary = "Yeni hayvan ekler")
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> save(@Valid @RequestBody AnimalRequestDTO dto) {
        AnimalResponseDTO savedAnimal = animalService.save(dto);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm hayvanları listeler")
    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> getAll() {
        return ResponseEntity.ok(animalService.getAll());
    }

    @Operation(summary = "ID ile hayvan getirir")
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getById(id));
    }

    @Operation(summary = "ID ile hayvan siler")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
