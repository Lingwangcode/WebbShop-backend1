package com.example.webbshopbackend1;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.ItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebbShopBackend1Application {

	public static void main(String[] args) {
		SpringApplication.run(WebbShopBackend1Application.class, args);
	}

	@Bean
	public CommandLineRunner bootstrap(ItemRepo itemRepo){
		return (args) -> {
			Item item1 = new Item("Sweatshirt", 499);
			Item item2 = new Item("Tophat", 299);
			Item item3 = new Item("Cool jeans", 799);
			Item item4 = new Item("Pearl bracelet", 1299);
			Item item5 = new Item("White t-shirt", 99);

			itemRepo.save(item1);
			itemRepo.save(item2);
			itemRepo.save(item3);
			itemRepo.save(item4);
			itemRepo.save(item5);
		};
	}

}
