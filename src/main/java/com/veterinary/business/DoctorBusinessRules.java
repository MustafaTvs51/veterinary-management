package com.veterinary.business;

import com.veterinary.exception.BusinessException;
import com.veterinary.exception.DuplicateResourceException;
import com.veterinary.model.Doctor;
import com.veterinary.repository.DoctorRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public void checkIfDoctorEmailExistsForUpdate(String email, Long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent() && !doctor.get().getId().equals(doctorId)) {
            throw new BusinessException("Bu email zaten başka bir doktora ait.");
        }
    }

}
