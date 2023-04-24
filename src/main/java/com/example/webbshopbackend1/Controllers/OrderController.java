package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Orders;
import com.example.webbshopbackend1.Repos.CustomerRepo;
import com.example.webbshopbackend1.Repos.OrderRepo;
import jakarta.persistence.criteria.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepo orderRepo;
    private CustomerRepo customerRepo;

    OrderController(OrderRepo orderRepo, CustomerRepo customerRepo){
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
    }
    @RequestMapping("/findAll")
    public List<Orders> getAllOrders(){
        return orderRepo.findAll();
    }
    @RequestMapping("/customerId/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable Long customerId){
        List<Orders> orders = orderRepo.findAll();
        Orders order = new Orders();
        return order.findOrderByCustomerId(customerId, orders);
    }


}
