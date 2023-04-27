package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Orders;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orderHTML")
public class OrderHTMLController {

    private final OrderRepo orderRepo;

    public OrderHTMLController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @RequestMapping("getAll")
    public String getAllOrders(Model model){
        List<Orders> orders = orderRepo.findAll();
        model.addAttribute("allOrders", orders);
        model.addAttribute("date", "Date");
        model.addAttribute("customerName", "Customer name");
        model.addAttribute("customerSsn", "Customer ssn");
        model.addAttribute("date", "Date");
        model.addAttribute("itemName", "Item name");
        model.addAttribute("itemPrice", "Price");

        model.addAttribute("itemInfo", "Item");
        model.addAttribute("customerInfo", "Customer info");

        return "orders";

    }

    /*@RequestMapping("/getByCustomerId/{id}")
    public String getOrdersByCustomerId(@PathVariable Long id, Model model){

        return "";

    }
     */



}
