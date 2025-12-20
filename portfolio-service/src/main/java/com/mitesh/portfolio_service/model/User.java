package com.mitesh.portfolio_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.List;
import java.util.SortedSet;

@Entity
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //private String password;
    private SortedSet<String> followedcoins;
    @OneToOne
    private Account account;
}
