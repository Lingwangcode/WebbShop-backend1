package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Repos.ItemRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/items")
public class ItemController {
    private final ItemRepo itemRepo;

    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @RequestMapping("/getAll")
    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }
    @RequestMapping("/getById/{id}")
    public Item getItems(@PathVariable Long id){
        return itemRepo.findById(id).get();
    }

    @RequestMapping("/add/{name}/{price}")
    public List<Item> addItem(@PathVariable String name, @PathVariable int price){
        Item item = new Item(name, price);
        itemRepo.save(item);
        return itemRepo.findAll();
        //return "Item added! " + name + " " + price + " SEK"; //Om vi vill returnera en sträng
    }

}
