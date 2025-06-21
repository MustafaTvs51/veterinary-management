package com.veterinary.repository;

import com.veterinary.model.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    List<AvailableDate> findByDoctorId(Long doctorId);

    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);

    void deleteByDoctorId(Long doctorId);
}
