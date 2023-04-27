package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping ("/customerHTML")
public class CustomerHTMLController {
    private final CustomerRepo customerRepo;

    public CustomerHTMLController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/getAll")
    public String getAllCustomers (Model model){
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("headline", "All customers");
        return "allCustomers.html";
    }

    @RequestMapping("/getCards")
    public String getAllCustomersWCards (Model model){
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("headline", "All customers");
        return "allCustomersWCards.html";
    }



    @RequestMapping("/getById/{id}")
    public String getById (@PathVariable Long id, Model model){
        Customer customer = customerRepo.findById(id).orElse(null);
        model.addAttribute("fullname", customer.getName());
        model.addAttribute("ssn", customer.getSocialSecurityNumber());
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("headline", "Specified customer");
        return "oneCustomer.html";
    }

    @RequestMapping("/getByIdCard/{id}")
    public String getByIdCard (@PathVariable Long id, Model model){
        Customer customer = customerRepo.findById(id).orElse(null);
        model.addAttribute("fullname", customer.getName());
        model.addAttribute("ssn", customer.getSocialSecurityNumber());
        model.addAttribute("id", customer.getId());
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("idTitle", "Customer ID");
        model.addAttribute("headline", "All information on customer");
        return "oneCustomerCard.html";
    }

    @RequestMapping("/addByForm")
    public String addByForm(Model model ){
        return "addCustomerForm.html";
    }
    @PostMapping("/save")
    public String save (@RequestParam String fullname,
                        @RequestParam String ssn, Model model){
        customerRepo.save(new Customer(fullname,ssn));
        return getAllCustomers(model);
    }

    @RequestMapping("/addByFormCard")
    public String addByFormCard(Model model ){
        model.addAttribute("headline", "Add customer");
        return "addCustomerFormCard.html";
    }
    @PostMapping("/saveCard")
    public String saveCard (@RequestParam String fullname,
                        @RequestParam String ssn, Model model){
        customerRepo.save(new Customer(fullname,ssn));
        return getAllCustomersWCards(model);
    }

}
