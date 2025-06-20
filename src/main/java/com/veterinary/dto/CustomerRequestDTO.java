package com.veterinary.dto;

public record CustomerRequestDTO(
        String name,
        String phone,
        String mail,
        String address,
        String city
) {}
