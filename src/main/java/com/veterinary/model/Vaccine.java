package com.veterinary.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate protectionStartDate;

    private LocalDate protectionEndDate;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}
