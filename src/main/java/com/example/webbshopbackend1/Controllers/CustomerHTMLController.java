package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/addByForm")
    public String addByForm(Model mode ){
        return "addCustomerForm.html";
    }
    @PostMapping("/save")
    public String save (@RequestParam String fullname,
                        @RequestParam String ssn, Model model){
        customerRepo.save(new Customer(fullname,ssn));
        return getAllCustomers(model);
    }


    //den här borde jag kunna återanvända sen för getById istället
    @PostMapping("/addCustomerResponse")
    public String greetingReceiver(@RequestParam String fullname, @RequestParam String ssn,
                                   Model model){
        model.addAttribute("fullname", fullname);
        model.addAttribute("ssn", ssn);
        return "addCustomerResponse.html";
    }

}
