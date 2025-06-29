package com.veterinary.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.LAZY) // Hayvan-aşı ilişkisi: çok aşı → bir hayvan
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}

