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

	/*@Bean
	public CommandLineRunner bootstrap(CustomerRepo customerRepo, ItemRepo itemRepo, PurchaseRepo purchaseRepo){
		return (args) -> {
			Item item1 = new Item("Sweatshirt", 499);

		}
	}*/

}
