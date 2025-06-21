package com.veterinary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequestDTO {

    @NotBlank(message = "Doktor adı boş olamaz.")
    private String firstName;

    @NotBlank(message = "Doktor soyadı boş olamaz.")
    private String lastName;

    @NotBlank(message = "Telefon numarası boş olamaz.")
    private String phone;

    @Email(message = "Geçerli bir email adresi giriniz.")
    private String email;
}
