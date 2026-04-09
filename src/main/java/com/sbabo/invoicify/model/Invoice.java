package com.sbabo.invoicify.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String status;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
