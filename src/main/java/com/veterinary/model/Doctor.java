package com.veterinary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "İsim boş olamaz")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Soyisim boş olamaz")
    private String lastName;

    private String specialty;

    @Column(nullable = false)
    @NotBlank(message = "Telefon numarası boş olamaz")
    private String phone;

    @Column(nullable = false, unique = true)
    @Email(message = "Geçerli bir email adresi giriniz")
    private String email;

    // Lombok ile getter ve setter otomatik
}
