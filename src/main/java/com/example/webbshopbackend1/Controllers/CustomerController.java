package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Repos.CustomerRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController         //eller ska det vara bara @Controller eftersom vi ska jobba med templating sen?
@RequestMapping ("/customer")   //för att kunna 'återanvända' samma url:ar för endpoints i olika controllers
public class CustomerController {

    private final  CustomerRepo cr;

    public CustomerController(CustomerRepo cr) {
        this.cr = cr;
    }


}