package com.veterinary.controller;

import com.veterinary.dto.AnimalRequestDTO;
import com.veterinary.dto.AnimalResponseDTO;
import com.veterinary.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Yeni hayvan ekler", description = "Geçerli hayvan bilgileri ile yeni hayvan oluşturur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hayvan başarıyla oluşturuldu"),
            @ApiResponse(responseCode = "400", description = "Geçersiz hayvan bilgisi")
    })
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> save(@Valid @RequestBody AnimalRequestDTO dto) {
        AnimalResponseDTO savedAnimal = animalService.save(dto);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm hayvanları listeler", description = "Sistemdeki tüm hayvanları getirir.")
    @ApiResponse(responseCode = "200", description = "Hayvanlar başarıyla listelendi")
    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> getAll() {
        return ResponseEntity.ok(animalService.getAll());
    }

    @Operation(summary = "ID ile hayvan getirir", description = "Verilen ID'ye sahip hayvanı getirir.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hayvan başarıyla bulundu"),
            @ApiResponse(responseCode = "404", description = "Hayvan bulunamadı")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getById(id));
    }

    @Operation(summary = "ID ile hayvan siler", description = "Verilen ID'ye sahip hayvanı siler.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hayvan başarıyla silindi"),
            @ApiResponse(responseCode = "404", description = "Hayvan bulunamadı")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
