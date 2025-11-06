package com.mitesh.portfolio_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;  //buy?sell, closed
    private double startamount;
    private double closeamount;
    private LocalDateTime startDateTime;
    private LocalDateTime closeDateTime;
    @ManyToOne
    private Account account;
}
