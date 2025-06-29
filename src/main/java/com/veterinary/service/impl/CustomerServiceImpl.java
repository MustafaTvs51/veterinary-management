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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    private final CustomerMapper customerMapper; // Mapper eklenmeli

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO dto) {
        log.info("Yeni müşteri kaydı: {} {}", dto.getFirstName(), dto.getLastName());
        customerBusinessRules.checkIfCustomerWithSameNameExists(dto.getFirstName(), dto.getLastName());
        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        log.debug("Kaydedilen müşteri ID: {}", saved.getId());
        return customerMapper.toDTO(saved);
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        log.info("Tüm müşteriler getiriliyor.");
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        log.info("ID ile müşteri aranıyor: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public void delete(Long id) {
        log.warn("Silme işlemi başlatıldı - ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        customerRepository.delete(customer);
        log.info("Müşteri silindi - ID: {}", id);
    }

    @Override
    public List<CustomerResponseDTO> searchByName(String name) {
        log.info("İsim ile müşteri aranıyor: {}", name);
        List<Customer> customers = customerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO dto) {
        log.info("Müşteri güncelleniyor - ID: {}", id);

        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));

        // Gerekirse iş kuralları ekle (aynı isimli başka müşteri var mı vs.)
        customerBusinessRules.checkIfCustomerNameConflict(id, dto.getFirstName(), dto.getLastName());

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());

        Customer updated = customerRepository.save(existing);

        log.debug("Müşteri güncellendi - ID: {}", updated.getId());

        return customerMapper.toDTO(updated);
    }


}
