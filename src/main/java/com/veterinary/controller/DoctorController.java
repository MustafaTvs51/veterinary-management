package com.veterinary.controller;

import com.veterinary.dto.DoctorRequestDTO;
import com.veterinary.dto.DoctorResponseDTO;
import com.veterinary.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@Tag(name = "Doctor Controller", description = "Doktor işlemleri için API")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(summary = "Yeni doktor ekler")
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> save(@Valid @RequestBody DoctorRequestDTO dto) {
        DoctorResponseDTO savedDoctor = doctorService.save(dto);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm doktorları getirir")
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAll() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @Operation(summary = "ID'ye göre doktor getirir")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @Operation(summary = "ID'ye göre doktor siler")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
