package com.example.webbshopbackend1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemHTMLController {

    @RequestMapping("/index")
    public String helloWorld(@RequestParam String name, Model model){ //Model är vår "plastpåse"
        model.addAttribute("name", name);
        return "index.html";
    }
}
