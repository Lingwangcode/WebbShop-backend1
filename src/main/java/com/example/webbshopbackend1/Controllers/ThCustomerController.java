package com.example.webbshopbackend1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThCustomerController {

    @RequestMapping("/addCustomerForm")
    public String addCustomerForm(){
        return "addCustomerForm.html";
    }


    @PostMapping("/addCustomerResponse")
    public String greetingReceiver(@RequestParam String fullname, @RequestParam String ssn,
                                   Model model){
        model.addAttribute("fullname", fullname);
        model.addAttribute("ssn", ssn);
        return "addCustomerResponse.html";
    }

}
