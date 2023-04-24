package com.example.webbshopbackend1.Repos;

import com.example.webbshopbackend1.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
