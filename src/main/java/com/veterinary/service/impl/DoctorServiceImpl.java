package com.veterinary.service.impl;

import com.veterinary.business.DoctorBusinessRules;
import com.veterinary.dto.DoctorRequestDTO;
import com.veterinary.dto.DoctorResponseDTO;
import com.veterinary.exception.ResourceNotFoundException;
import com.veterinary.mapper.DoctorMapper;
import com.veterinary.model.Doctor;
import com.veterinary.repository.DoctorRepository;
import com.veterinary.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorBusinessRules doctorBusinessRules;

    @Override
    public DoctorResponseDTO save(DoctorRequestDTO dto) {
        doctorBusinessRules.checkIfDoctorEmailExists(dto.getEmail());
        Doctor doctor = DoctorMapper.toEntity(dto);
        Doctor saved = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(saved);
    }

    @Override
    public DoctorResponseDTO getById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li doktor bulunamadı"));
        return DoctorMapper.toDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAll() {
        return doctorRepository.findAll().stream()
                .map(DoctorMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li doktor bulunamadı"));
        doctorRepository.delete(doctor);
    }
}
