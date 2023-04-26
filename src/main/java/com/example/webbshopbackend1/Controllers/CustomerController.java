package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.web.bind.annotation.*;

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
    //curl http://localhost:8080/customers/add -H "Content-Type:application/json" -d "{\"name\":\"baby\", \"socialSecurityNumber\":\"222222\"}" -v
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        customerRepo.save(customer);
        return customerRepo.findById(customer.getId()).get();
    }

}