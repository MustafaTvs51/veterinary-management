package com.veterinary.business;

import com.veterinary.exception.DuplicateResourceException;
import com.veterinary.repository.AnimalRepository;
import com.veterinary.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerBusinessRules {

    private final CustomerRepository customerRepository;

    public void checkIfCustomerWithSameNameExists(String firstName, String lastName) {
        boolean exists = customerRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
        if (exists) {
            throw new DuplicateResourceException("Bu isim ve soyisimle zaten bir müşteri var: " +
                    firstName + " " + lastName);
        }
    }
    private final AnimalRepository animalRepository;

    public void checkIfCustomerHasAnimal(Long customerId) {
        boolean hasAnimal = animalRepository.existsByOwnerId(customerId);
        if (hasAnimal) {
            throw new IllegalStateException("Bu müşteri silinemez. İlişkili hayvan(lar) mevcut.");
        }
    }
    public void checkIfCustomerNameConflict(Long id, String firstName, String lastName) {
        boolean exists = customerRepository.existsByFirstNameAndLastNameAndIdNot(firstName, lastName, id);
        if (exists) {
            throw new RuntimeException("Aynı isimde başka bir müşteri zaten mevcut.");
        }
    }


}