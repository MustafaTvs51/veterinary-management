package com.veterinary.business;

import com.veterinary.exception.DuplicateResourceException;
import com.veterinary.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorBusinessRules {

    private final DoctorRepository doctorRepository;

    public void checkIfDoctorEmailExists(String email) {
        boolean exists = doctorRepository.existsByEmailIgnoreCase(email);
        if (exists) {
            throw new DuplicateResourceException("Bu e-posta ile kayıtlı bir doktor zaten var: " + email);
        }
    }
}
