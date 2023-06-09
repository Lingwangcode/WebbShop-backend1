package com.example.webbshopbackend1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor     //för testning
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String socialSecurityNumber;

    public Customer(String name, String socialSecurityNumber) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}


