package com.veterinary.dto;

import com.veterinary.model.Doctor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponseDTO {


        private Long id;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String address;
        private String city;

    }

    
