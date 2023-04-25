package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController         //eller ska det vara bara @Controller eftersom vi ska jobba med templating sen?
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/getAll")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @RequestMapping("/getById/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    @RequestMapping("/add/{name}/{socSec}")
    public List<Customer> addCustomer(@PathVariable String name, @PathVariable String socSec) {
        Customer customer = new Customer(name, socSec);
        customerRepo.save(customer);
        return customerRepo.findAll();
    }
}