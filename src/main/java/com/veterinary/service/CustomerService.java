package com.veterinary.service;

import com.veterinary.dto.CustomerRequestDTO;
import com.veterinary.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO save(CustomerRequestDTO dto);

    List<CustomerResponseDTO> getAll();

    CustomerResponseDTO getById(Long id);

    void delete(Long id);
}
