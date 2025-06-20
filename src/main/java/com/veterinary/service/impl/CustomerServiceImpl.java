package com.veterinary.service.impl;

import com.veterinary.business.CustomerBusinessRules;
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
    private final CustomerBusinessRules customerBusinessRules;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerBusinessRules customerBusinessRules) {
        this.customerRepository = customerRepository;
        this.customerBusinessRules = customerBusinessRules;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO dto) {
        customerBusinessRules.checkIfCustomerWithSameNameExists(dto.getFirstName(), dto.getLastName());
        Customer customer = CustomerMapper.toEntity(dto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " id'li müşteri bulunamadı"));
        customerRepository.delete(customer);
    }

}
