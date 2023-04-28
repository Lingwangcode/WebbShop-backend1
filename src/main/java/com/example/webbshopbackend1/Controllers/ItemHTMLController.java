package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Models.Orders;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.ItemRepo;
import com.example.webbshopbackend1.Repos.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/itemHTML")
public class ItemHTMLController {

    private final ItemRepo itemRepo;
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;

    public ItemHTMLController(ItemRepo itemRepo, OrderRepo orderRepo, CustomerRepo customerRepo) {
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }


    @RequestMapping("/getAll")
    public String items(Model model) { //Model är vår "plastpåse"
        List<Item> itemList = itemRepo.findAll();
        model.addAttribute("items", itemList);
        return "items.html";
    }
/*
    @RequestMapping(path = "/deleteById/{id}")
    public String deleteItem(@PathVariable Long id, Model model){
        itemRepo.deleteById(id);
        return items(model);
    }*/

    @RequestMapping( "/addItem")
    public String itemAdded(@RequestParam String itemName, @RequestParam int itemPrice, Model model){
        itemRepo.save(new Item(itemName, itemPrice));
        return items(model);
    }

    @RequestMapping("/buyItemPage/{id}")
    public String buyItemPageWithId(@PathVariable Long id, Model model){
        Item item = itemRepo.findById(id).get();
        model.addAttribute("item", item);
        return "buyItem.html";
    }

    /*
    @RequestMapping("/buyItemPage")
    public String buyItemPage(Model model){
        return "buyItem.html";
    }*/

    @RequestMapping(path = "/buy/{itemId}/{customerId}")
    public String addOrder(@PathVariable Long customerId, @PathVariable Long itemId) {
        Item item = itemRepo.findById(itemId).get();
        Customer customer = customerRepo.findById(customerId).orElse(null); //orElse(null) krävs för att inte få 500-fel om obefintligt ID anges
        if (item != null && customer != null) {
            orderRepo.save(new Orders(LocalDate.now(), customer, List.of(item)));
            return "orders.html";
        } else {
            return "buyItem.html";
        }

    }
}
