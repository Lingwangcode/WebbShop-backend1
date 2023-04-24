package com.example.webbshopbackend1.Repos;

import com.example.webbshopbackend1.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
