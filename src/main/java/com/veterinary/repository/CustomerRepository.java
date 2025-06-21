package com.veterinary.repository;

import com.veterinary.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // İsimde arama yapmak için (firstName alanında)
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    // Soyisimde arama yapmak için
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    // İsim veya soyisimde arama yapmak için
    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    // CustomerBusinessRules'ta kullanmak için:
    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
