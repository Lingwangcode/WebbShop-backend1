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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/itemHTML")
public class ItemHTMLController {
    private final OrderHTMLController orderHTMLController;

    private final ItemRepo itemRepo;
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;

    public ItemHTMLController(OrderHTMLController orderHTMLController, ItemRepo itemRepo, OrderRepo orderRepo, CustomerRepo customerRepo) {
        this.orderHTMLController = orderHTMLController;
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/getAll")
    public String items(Model model) {
        List<Item> itemList = itemRepo.findAll();
        model.addAttribute("items", itemList);
        return "items.html";
    }

    @RequestMapping("/addItem")
    public String itemAdded(@RequestParam String itemName, @RequestParam String itemPrice,
                            @RequestParam String itemStock, Model model) {
        if (itemPrice == null || itemPrice.isEmpty()) {
            model.addAttribute("errorMessage", "Price conditions not met");
            return items(model);
        }
        if (itemStock == null || itemStock.isEmpty()) {
            model.addAttribute("errorMessage", "Stock conditions not met");
            return items(model);
        }
        if(itemName == null || itemName.isEmpty()){
            model.addAttribute("errorMessage", "Item name not added");
            return items(model);
        }
        try {
            int price = Integer.parseInt(itemPrice);
            int stock = Integer.parseInt(itemStock);
            if(price > 0) {
                itemRepo.save(new Item(itemName, price, stock));
            } else {
                model.addAttribute("errorMessage", "Price has to be above 0");
            }
            return items(model);
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Price and stock has to be given in numbers");
            return items(model);
        }
    }

    @RequestMapping("/buyItemPage/{id}")
    public String buyItemPageWithId(@PathVariable Long id, Model model){
        List<Customer> customers = customerRepo.findAll()
                .stream().filter(customer -> customer.getName() != null).toList();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("nameTitle", "Full name");
        model.addAttribute("ssnTitle", "Social security number");
        model.addAttribute("headline", "All customers");
        Item item = itemRepo.findById(id).get();
        item.setStock(item.getStock()-1);
        itemRepo.save(item);
        model.addAttribute("item", item);
        return "buyItem";
    }

    @RequestMapping(path = "/buy")
    public String addOrder(@RequestParam Long customerId, @RequestParam Long itemId, Model model) {
        Item item = itemRepo.findById(itemId).get();
        Customer customer = customerRepo.findById(customerId).orElse(null); //orElse(null) krävs för att inte få 500-fel om obefintligt ID anges
        if (item != null && customer != null) {
            orderRepo.save(new Orders(LocalDate.now(), customer, List.of(item)));
            return "redirect:/orderHTML/getAll";
        } else {
            return "redirect:/orderHTML/getAll";
        }
    }
}
