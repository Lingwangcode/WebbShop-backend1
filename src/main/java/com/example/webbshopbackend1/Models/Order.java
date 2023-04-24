package com.example.webbshopbackend1.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany
    private List<Item> items = new ArrayList<>();

}
