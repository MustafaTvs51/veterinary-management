package com.veterinary.controller;

import com.veterinary.dto.CustomerRequestDTO;
import com.veterinary.dto.CustomerResponseDTO;
import com.veterinary.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Controller", description = "Müşteri işlemleri için API")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Yeni müşteri ekler", description = "Geçerli müşteri bilgileri ile yeni müşteri oluşturur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Müşteri başarıyla oluşturuldu"),
            @ApiResponse(responseCode = "400", description = "Geçersiz müşteri bilgisi")
    })
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO savedCustomer = customerService.save(dto);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm müşterileri listeler", description = "Sistemdeki tüm müşterileri getirir.")
    @ApiResponse(responseCode = "200", description = "Müşteriler başarıyla listelendi")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @Operation(summary = "ID ile müşteri getirir", description = "Verilen ID'ye sahip müşteriyi getirir.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Müşteri başarıyla bulundu"),
            @ApiResponse(responseCode = "404", description = "Müşteri bulunamadı")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @Operation(summary = "ID ile müşteri siler", description = "Verilen ID'ye sahip müşteriyi siler.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Müşteri başarıyla silindi"),
            @ApiResponse(responseCode = "404", description = "Müşteri bulunamadı")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
