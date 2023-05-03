package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Models.Orders;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.OrderRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepo customerRepo;
    private final OrderRepo orderRepo;

    public CustomerController(CustomerRepo customerRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    @RequestMapping("/getAll")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @RequestMapping("/getById/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    @RequestMapping("/deleteById/{id}")
    public String delete(@PathVariable Long id) {
        List<Orders> orders = orderRepo.findByCustomerId(id);
        if (orders.isEmpty()) {
            customerRepo.deleteById(id);
            return "Customer deleted";
        } else {
            Customer customer = customerRepo.findById(id).orElse(null);
            customer.setName(null);
            customer.setSocialSecurityNumber(null);
            customerRepo.save(customer);
            return "All information concerning customer has been deleted";
        }
    }

    //curl http://localhost:8080/customers/add -H "Content-Type:application/json" -d "{\"name\":\"baby\", \"socialSecurityNumber\":\"222222\"}" -v
    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null || customer.getSocialSecurityNumber() == null
        || customer.getName().length() <1 || customer.getSocialSecurityNumber().length() <1) {
            return "\t\tAll customers must have a name and social security number";
        } else {
            customerRepo.save(customer);
            return "\t\tCustomer " + customer.getName() + " added to database";
        }
    }
}