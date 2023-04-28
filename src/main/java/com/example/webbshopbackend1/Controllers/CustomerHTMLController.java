package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customerHTML")
public class CustomerHTMLController {
    private final CustomerRepo customerRepo;

    public CustomerHTMLController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/getAll")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("headline", "All customers");
        return "all-customers.html";
    }

    @RequestMapping("/getById/{id}")
    public String getById(@PathVariable Long id, Model model) {

        Customer customer = customerRepo.findById(id).orElse(null);
        model.addAttribute("fullname", customer.getName());
        model.addAttribute("ssn", customer.getSocialSecurityNumber());
        model.addAttribute("id", customer.getId());
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("idTitle", "Customer ID");
        model.addAttribute("deleted", false);

        return "one-customer.html";
    }

    @RequestMapping("/deleteById/{id}")
    public String delete(@PathVariable Long id, Model model) {
        customerRepo.deleteById(id);
        model.addAttribute("message", "CUSTOMER DELETED!");
        model.addAttribute("deleted", true);
        return "one-customer.html";
    }

    @PostMapping("/save")
    public String save(@RequestParam String fullname,
                       @RequestParam String ssn, Model model) {
        customerRepo.save(new Customer(fullname, ssn));
        return getAllCustomers(model);
    }

}
