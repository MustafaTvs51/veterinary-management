package com.veterinary.controller;

import com.veterinary.dto.AvailableDateRequestDTO;
import com.veterinary.dto.AvailableDateResponseDTO;
import com.veterinary.service.AvailableDateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-dates")
@Tag(name = "AvailableDate Controller", description = "Doktorların müsait günleri için API")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @Operation(summary = "Yeni müsait gün ekler")
    @PostMapping
    public ResponseEntity<AvailableDateResponseDTO> save(@Valid @RequestBody AvailableDateRequestDTO dto) {
        AvailableDateResponseDTO saved = availableDateService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm müsait günleri listeler")
    @GetMapping
    public ResponseEntity<List<AvailableDateResponseDTO>> getAll() {
        return ResponseEntity.ok(availableDateService.getAll());
    }

    @Operation(summary = "ID'ye göre müsait günü siler")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        availableDateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
