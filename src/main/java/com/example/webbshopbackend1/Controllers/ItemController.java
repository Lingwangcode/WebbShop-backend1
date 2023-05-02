package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Repos.ItemRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/items")
public class ItemController {
    private final ItemRepo itemRepo;

    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @RequestMapping("/getAll")
    public List<Item> getItems(){
        return itemRepo.findAll();
    }
    @RequestMapping("/getById/{id}")
    public Item getItems(@PathVariable Long id){
        return itemRepo.findById(id).get();
    }

    //curl http://localhost:8080/items/add -H "Content-Type:application/json" -d "{\"name\":\"Lola-shirt\", \"price\":1745, \"stock\":5}" -v
    @PostMapping("/add")
    public String addItem(@RequestBody Item item){
        itemRepo.save(item);
        return "Saved " + item.getName();
    }
}
