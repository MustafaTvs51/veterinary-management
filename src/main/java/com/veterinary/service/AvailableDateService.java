package com.veterinary.service;

import com.veterinary.business.AvailableDateBusinessRules;
import com.veterinary.dto.AvailableDateRequestDTO;
import com.veterinary.dto.AvailableDateResponseDTO;
import com.veterinary.model.AvailableDate;
import com.veterinary.model.Doctor;
import com.veterinary.repository.AvailableDateRepository;
import com.veterinary.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final AvailableDateBusinessRules availableDateBusinessRules;

    public AvailableDateResponseDTO save(AvailableDateRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));

        availableDateBusinessRules.checkIfDateAlreadyExistsForDoctor(doctor.getId(), dto.getAvailableDate());

        AvailableDate availableDate = new AvailableDate();
        availableDate.setDoctor(doctor);
        availableDate.setAvailableDate(dto.getAvailableDate());

        availableDate = availableDateRepository.save(availableDate);

        AvailableDateResponseDTO responseDTO = new AvailableDateResponseDTO();
        responseDTO.setId(availableDate.getId());
        responseDTO.setAvailableDate(availableDate.getAvailableDate());
        responseDTO.setDoctorId(doctor.getId());

        return responseDTO;
    }

    public List<AvailableDateResponseDTO> getAll() {
        return availableDateRepository.findAll()
                .stream()
                .map(ad -> {
                    AvailableDateResponseDTO dto = new AvailableDateResponseDTO();
                    dto.setId(ad.getId());
                    dto.setAvailableDate(ad.getAvailableDate());
                    dto.setDoctorId(ad.getDoctor().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!availableDateRepository.existsById(id)) {
            throw new RuntimeException(id + " id'li müsait gün bulunamadı.");
        }
        availableDateRepository.deleteById(id);
    }
}
