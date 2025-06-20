package com.veterinary.dto;

public record CustomerResponseDTO(
        Long id,
        String name,
        String phone,
        String mail,
        String city
) {}
