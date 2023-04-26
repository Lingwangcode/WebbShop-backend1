package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Customer;
import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Models.Orders;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.ItemRepo;
import com.example.webbshopbackend1.Repos.OrderRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;

    OrderController(OrderRepo orderRepo, CustomerRepo customerRepo, ItemRepo itemRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
    }

    @RequestMapping("/getAll")
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    @RequestMapping("/getByCustomerId/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Orders> orders = orderRepo.findAll();
        List<Orders> customerOrders = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getCustomer().getId() == customerId) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

  /*  @RequestMapping("/buy/{customerId}/{itemId}")
    public String addOrder(@PathVariable Long customerId, @PathVariable Long itemId) {
        Item item = itemRepo.findById(itemId).get();
        Customer customer = customerRepo.findById(customerId).get();
        if (item != null && customer != null) {
            orderRepo.save(new Orders(LocalDate.now(), customer, List.of(item)));
            return "Order added";
        } else {
            return "Order failed";
        }
    }

   */

    @RequestMapping("/buy/{customerId}/{itemIds}")
    public String addOrder(@PathVariable Long customerId, @PathVariable List<Long> itemIds) {
        List<Item> items = new ArrayList<>();
        for (Long itemId :itemIds) {            //måste gå via en for-loop för att kunna lägga till flera av samma id i samma order
            Item item = itemRepo.findById(itemId).orElse(null);
            if (item != null) {
                items.add(item);
            }
        }
        Customer customer = customerRepo.findById(customerId).orElse(null); //orElse(null) krävs för att inte få 500-fel om obefintligt ID anges
        if (items != null && customer != null) {
            orderRepo.save(new Orders(LocalDate.now(), customer, items));
            return "Order added";
        } else {
            return "Order failed";
        }

    }

}
