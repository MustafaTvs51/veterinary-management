package com.veterinary.controller;

import com.veterinary.dto.DoctorRequestDTO;
import com.veterinary.dto.DoctorResponseDTO;
import com.veterinary.service.DoctorService;
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
@RequestMapping("/api/doctors")
@Tag(name = "Doctor Controller", description = "Doktor işlemleri için API")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(summary = "Yeni doktor ekler", description = "Geçerli doktor bilgileri ile yeni doktor oluşturur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doktor başarıyla oluşturuldu"),
            @ApiResponse(responseCode = "400", description = "Geçersiz doktor bilgisi")
    })
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> save(@Valid @RequestBody DoctorRequestDTO dto) {
        DoctorResponseDTO savedDoctor = doctorService.save(dto);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm doktorları getirir", description = "Sistemdeki tüm doktorları listeler.")
    @ApiResponse(responseCode = "200", description = "Doktorlar başarıyla listelendi")
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAll() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @Operation(summary = "ID'ye göre doktor getirir", description = "Verilen ID'ye sahip doktoru getirir.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doktor başarıyla bulundu"),
            @ApiResponse(responseCode = "404", description = "Doktor bulunamadı")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @Operation(summary = "ID'ye göre doktor siler", description = "Verilen ID'ye sahip doktoru siler.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Doktor başarıyla silindi"),
            @ApiResponse(responseCode = "404", description = "Doktor bulunamadı")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
