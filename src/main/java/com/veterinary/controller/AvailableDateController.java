package com.veterinary.controller;

import com.veterinary.dto.AvailableDateRequestDTO;
import com.veterinary.dto.AvailableDateResponseDTO;
import com.veterinary.model.AvailableDate;
import com.veterinary.service.AvailableDateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @Operation(summary = "Yeni müsait gün ekler")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Müsait gün başarıyla oluşturuldu"),
            @ApiResponse(responseCode = "400", description = "Geçersiz veri")
    })
    @PostMapping
    public ResponseEntity<AvailableDateResponseDTO> save(@Valid @RequestBody AvailableDateRequestDTO dto) {
        AvailableDateResponseDTO saved = availableDateService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "ID'ye göre müsait gün getirir")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Müsait gün başarıyla bulundu"),
            @ApiResponse(responseCode = "404", description = "Müsait gün bulunamadı")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDTO> getById(@PathVariable Long id) {
        AvailableDate availableDate = availableDateService.getById(id);

        AvailableDateResponseDTO dto = new AvailableDateResponseDTO();
        dto.setId(availableDate.getId());
        dto.setDoctorId(availableDate.getDoctor().getId());
        dto.setAvailableDate(availableDate.getAvailableDate());

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Tüm müsait günleri listeler")
    @ApiResponse(responseCode = "200", description = "Müsait günler başarıyla listelendi")
    @GetMapping
    public ResponseEntity<List<AvailableDateResponseDTO>> getAll() {
        return ResponseEntity.ok(availableDateService.getAll());
    }

    @Operation(summary = "ID'ye göre müsait gün siler")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Müsait gün başarıyla silindi"),
            @ApiResponse(responseCode = "404", description = "Müsait gün bulunamadı")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        availableDateService.delete(id);
        return ResponseEntity.ok("Müsait gün başarıyla silindi.");
    }

    @Operation(summary = "Doktorun müsait günlerini getirir")
    @ApiResponse(responseCode = "200", description = "Müsait günler başarıyla listelendi")
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailableDateResponseDTO>> getAvailableDatesForDoctor(@PathVariable Long doctorId) {
        List<AvailableDateResponseDTO> dates = availableDateService.getAvailableDatesForDoctor(doctorId);
        return ResponseEntity.ok(dates);
    }

    @Operation(summary = "ID'ye göre müsait gün günceller")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Müsait gün başarıyla güncellendi"),
            @ApiResponse(responseCode = "404", description = "Müsait gün bulunamadı"),
            @ApiResponse(responseCode = "400", description = "Geçersiz veri")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AvailableDateRequestDTO dto) {
        AvailableDateResponseDTO updated = availableDateService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

}
