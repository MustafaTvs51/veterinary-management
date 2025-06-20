package com.veterinary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotBlank(message = "İsim boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyisim boş olamaz")
    private String lastName;
}
