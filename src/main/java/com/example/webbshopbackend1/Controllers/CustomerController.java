package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController         //eller ska det vara bara @Controller eftersom vi ska jobba med templating sen?
@RequestMapping ("/customer")   //för att kunna 'återanvända' samma url:ar för endpoints i olika controllers
public class CustomerController {

    private final  CustomerRepo cr;

    public CustomerController(CustomerRepo cr) {
        this.cr = cr;
    }

    @RequestMapping ("/getAll")
    public List<Customer> getAllCustomers (){
        return cr.findAll();
    }

    @RequestMapping ("/getById/{id}")
    public Customer getCustomerById (@PathVariable Long id){
        return cr.findById(id).orElse(null);
    }

}