package com.example.webbshopbackend1.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;

    @ManyToOne // (optional = false)    ifall vi vill att det måste finnas en kund kopplad till beställningen
    @JoinColumn
    private Customer customer;

    //@OneToMany        om en item bara ska kunna finnas med i en beställning
    @ManyToMany         //om samma item ska kunna finnas med i flera beställningar
    @JoinTable
    private List<Item> items = new ArrayList<>();

    public Orders(LocalDate ld, Customer customer, List<Item> items){
        this.customer=customer;
        this.date = ld;
        this.items = items;
    }


}
