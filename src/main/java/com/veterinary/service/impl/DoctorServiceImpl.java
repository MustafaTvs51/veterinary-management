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

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorBusinessRules doctorBusinessRules;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorResponseDTO save(DoctorRequestDTO dto) {
        doctorBusinessRules.checkIfDoctorEmailExists(dto.getEmail());
        Doctor doctor = doctorMapper.toEntity(dto);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toDTO(saved);
    }

    @Override
    public DoctorResponseDTO getById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li doktor bulunamadı"));
        return doctorMapper.toDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li doktor bulunamadı"));
        doctorRepository.delete(doctor);
    }
    @Override
    public List<DoctorResponseDTO> searchByName(String name) {
        List<Doctor> doctors = doctorRepository.findByFirstNameContainingIgnoreCase(name);
        return doctors.stream()
                .map(doctorMapper::toDTO)
                .toList();
    }
    @Override
    public DoctorResponseDTO update(Long id, DoctorRequestDTO dto) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li doktor bulunamadı"));

        // İş kuralı kontrolü (örnek: aynı email başka doktora ait mi diye kontrol)
        doctorBusinessRules.checkIfDoctorEmailExistsForUpdate(dto.getEmail(), id);

        existingDoctor.setFirstName(dto.getFirstName());
        existingDoctor.setLastName(dto.getLastName());
        existingDoctor.setPhone(dto.getPhone());
        existingDoctor.setEmail(dto.getEmail());
        // Gerekirse diğer alanlar burada güncellenebilir...

        Doctor updated = doctorRepository.save(existingDoctor);
        return doctorMapper.toDTO(updated);
    }


}
