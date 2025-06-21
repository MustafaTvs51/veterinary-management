package com.veterinary.service.impl;

import com.veterinary.business.CustomerBusinessRules;
import com.veterinary.dto.CustomerRequestDTO;
import com.veterinary.dto.CustomerResponseDTO;
import com.veterinary.exception.ResourceNotFoundException;
import com.veterinary.mapper.CustomerMapper;
import com.veterinary.model.Customer;
import com.veterinary.repository.CustomerRepository;
import com.veterinary.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j // Lombok ile Logger eklenir
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO dto) {
        log.info("Yeni müşteri kaydı: {} {}", dto.getFirstName(), dto.getLastName());
        customerBusinessRules.checkIfCustomerWithSameNameExists(dto.getFirstName(), dto.getLastName());
        Customer customer = CustomerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        log.debug("Kaydedilen müşteri ID: {}", saved.getId());
        return CustomerMapper.toDTO(saved);
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        log.info("Tüm müşteriler getiriliyor.");
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        log.info("ID ile müşteri aranıyor: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public void delete(Long id) {
        log.warn("Silme işlemi başlatıldı - ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        customerRepository.delete(customer);
        log.info("Müşteri silindi - ID: {}", id);
    }
}
