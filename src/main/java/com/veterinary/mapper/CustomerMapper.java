package com.veterinary.mapper;

import com.veterinary.dto.CustomerRequestDTO;
import com.veterinary.dto.CustomerResponseDTO;
import com.veterinary.model.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        Customer c = new Customer();
        c.setName(dto.name());
        c.setPhone(dto.phone());
        c.setMail(dto.mail());
        c.setAddress(dto.address());
        c.setCity(dto.city());
        return c;
    }

    public static CustomerResponseDTO toDTO(Customer c) {
        return new CustomerResponseDTO(c.getId(), c.getName(), c.getPhone(), c.getMail(), c.getCity());
    }
}
