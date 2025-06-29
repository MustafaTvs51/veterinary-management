package com.veterinary.controller;

import com.veterinary.dto.VaccineRequestDTO;
import com.veterinary.dto.VaccineResponseDTO;
import com.veterinary.service.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
@Tag(name = "Vaccine Controller", description = "Aşı işlemleri için controller sınıfı")
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    @Operation(summary = "Yeni aşı kaydı oluştur")
    public ResponseEntity<VaccineResponseDTO> save(@RequestBody VaccineRequestDTO dto) {
        return ResponseEntity.ok(vaccineService.save(dto));
    }

    @GetMapping
    @Operation(summary = "Tüm aşı kayıtlarını getir")
    public ResponseEntity<List<VaccineResponseDTO>> getAll() {
        return ResponseEntity.ok(vaccineService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID bilgisine göre aşı getir")
    public ResponseEntity<VaccineResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ID bilgisine göre aşıyı sil")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vaccineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/animal/{animalId}")
    @Operation(summary = "Hayvan ID’sine göre aşıları getir")
    public ResponseEntity<List<VaccineResponseDTO>> getByAnimalId(@PathVariable Long animalId) {
        return ResponseEntity.ok(vaccineService.getByAnimalId(animalId));
    }

    @GetMapping("/filter")
    @Operation(summary = "Koruma bitiş tarihine göre aşıları filtrele")
    public ResponseEntity<List<VaccineResponseDTO>> getByFinishDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(vaccineService.getByProtectionFinishDateBetween(start, end));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mevcut bir aşı kaydını güncelle")
    public ResponseEntity<VaccineResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody VaccineRequestDTO dto) {
        return ResponseEntity.ok(vaccineService.update(id, dto));
    }
}
