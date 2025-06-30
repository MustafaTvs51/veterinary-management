package com.veterinary.controller;

import com.veterinary.dto.AppointmentRequestDTO;
import com.veterinary.dto.AppointmentResponseDTO;
import com.veterinary.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointment Controller", description = "Randevu işlemleri için API")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(summary = "Yeni randevu ekler")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> save(@Valid @RequestBody AppointmentRequestDTO dto) {
        AppointmentResponseDTO saved = appointmentService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm randevuları listeler")
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAll() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @Operation(summary = "ID'ye göre randevu getirir")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @Operation(summary = "ID'ye göre randevu siler")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Doktor ID ve tarihe göre randevuları getirir")
    @GetMapping("/doctor")
    public ResponseEntity<List<AppointmentResponseDTO>> getByDoctorAndDate(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(appointmentService.getByDoctorIdAndDate(doctorId, date));
    }

    @Operation(summary = "Hayvan ID ve tarihe göre randevuları getirir")
    @GetMapping("/animal")
    public ResponseEntity<List<AppointmentResponseDTO>> getByAnimalAndDate(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(appointmentService.getByAnimalIdAndDate(animalId, date));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Mevcut randevuyu güncelle")
    public ResponseEntity<AppointmentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequestDTO dto
    ) {
        AppointmentResponseDTO updated = appointmentService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

}
