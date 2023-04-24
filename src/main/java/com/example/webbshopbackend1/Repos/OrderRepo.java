package com.example.webbshopbackend1.Repos;

import com.example.webbshopbackend1.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}

