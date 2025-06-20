package com.veterinary.service.impl;

import com.veterinary.dto.CustomerRequestDTO;
import com.veterinary.dto.CustomerResponseDTO;
import com.veterinary.exception.ResourceNotFoundException;
import com.veterinary.mapper.CustomerMapper;
import com.veterinary.model.Customer;
import com.veterinary.repository.CustomerRepository;
import com.veterinary.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        return CustomerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        return CustomerMapper.toDTO(c);
    }

    @Override
    public void delete(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        customerRepository.delete(c);
    }
}
