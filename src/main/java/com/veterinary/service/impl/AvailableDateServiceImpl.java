package com.veterinary.service.impl;

import com.veterinary.business.AvailableDateBusinessRules;
import com.veterinary.dto.AvailableDateRequestDTO;
import com.veterinary.dto.AvailableDateResponseDTO;
import com.veterinary.model.AvailableDate;
import com.veterinary.model.Doctor;
import com.veterinary.repository.AvailableDateRepository;
import com.veterinary.repository.DoctorRepository;
import com.veterinary.service.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailableDateServiceImpl implements AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    private final AvailableDateBusinessRules availableDateBusinessRules;

    @Override
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

    @Override
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

    @Override
    public void delete(Long id) {
        if (!availableDateRepository.existsById(id)) {
            throw new RuntimeException(id + " id'li müsait gün bulunamadı.");
        }
        availableDateRepository.deleteById(id);
        System.out.println(id + "müsait gün silindi");
    }

    @Override
    public List<AvailableDateResponseDTO> getAvailableDatesForDoctor(Long doctorId) {
        List<AvailableDate> availableDates = availableDateRepository.findByDoctorId(doctorId);

        return availableDates.stream()
                .map(ad -> {
                    AvailableDateResponseDTO dto = new AvailableDateResponseDTO();
                    dto.setId(ad.getId());
                    dto.setAvailableDate(ad.getAvailableDate());
                    dto.setDoctorId(ad.getDoctor().getId());
                    return dto;
                }).collect(Collectors.toList());
    }
    @Override
    public AvailableDate getById(Long id) {
        return availableDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Müsait gün bulunamadı: " + id));
    }
    @Override
    public AvailableDateResponseDTO update(Long id, AvailableDateRequestDTO dto) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " id'li kayıt bulunamadı"));

        availableDateBusinessRules.checkIfDateAlreadyExistsForDoctor(dto.getDoctorId(), dto.getAvailableDate());

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));

        availableDate.setDoctor(doctor);
        availableDate.setAvailableDate(dto.getAvailableDate());

        AvailableDate saved = availableDateRepository.save(availableDate);

        AvailableDateResponseDTO responseDTO = new AvailableDateResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setAvailableDate(saved.getAvailableDate());
        responseDTO.setDoctorId(saved.getDoctor().getId());

        return responseDTO;
    }

}
