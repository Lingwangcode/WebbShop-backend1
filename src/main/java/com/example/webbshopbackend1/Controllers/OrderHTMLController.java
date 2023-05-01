package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Models.Orders;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.ItemRepo;
import com.example.webbshopbackend1.Repos.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orderHTML")
public class OrderHTMLController {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;

    OrderHTMLController(OrderRepo orderRepo, CustomerRepo customerRepo, ItemRepo itemRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
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


    @RequestMapping("/save")
    public String addOrder(@RequestParam Long customerId, @ModelAttribute("itemIds") String itemIds, Model model){

        List<Item> items = new ArrayList<>();
        for (String itemId :itemIds.split(",")) {
            Long id = Long.parseLong(itemId.trim());
            Item item = itemRepo.findById(id).orElse(null);
            if (item != null) {
                items.add(item);
                item.setStock(item.getStock()-1);
            }
            else {
                model.addAttribute("errorMessage", "Item not found");
                return "orders";
            }
        }
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (items != null && customer != null) {
            orderRepo.save(new Orders(LocalDate.now(), customer, items));
            return getAllOrders(model);
        } else {
            model.addAttribute("errorMessage", "Customer not found");
            return "orders";
        }

    }

    @RequestMapping("/getByCustomerId/{customerId}")
    public String getOrdersByCustomerId(@PathVariable Long customerId, Model model){

        List<Orders> orders = orderRepo.findAll();
        List<Orders> customerOrders = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getCustomer().getId() == customerId) {
                customerOrders.add(order);
            }
        }
        model.addAttribute("allOrders", customerOrders);
        model.addAttribute("date", "Date");
        model.addAttribute("customerName", "Customer name");
        model.addAttribute("customerSsn", "Customer ssn");
        model.addAttribute("date", "Date");
        model.addAttribute("itemName", "Item name");
        model.addAttribute("itemPrice", "Price");

        model.addAttribute("itemInfo", "Item");
        model.addAttribute("customerInfo", "Customer info");

        return "orders-by-customer-id";


    }


}
