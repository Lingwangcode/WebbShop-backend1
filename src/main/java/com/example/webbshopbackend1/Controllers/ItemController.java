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

    @RequestMapping("")
    public List<Item> getItems(){
        return itemRepo.findAll();
    }
    @RequestMapping("/{id}")
    public Item getItems(@PathVariable Long id){
        return itemRepo.findById(id).get();
    }

    @PostMapping("/add")
    public Item addItem(@RequestBody Item item){
        itemRepo.save(item);
        return itemRepo.findById(item.getId()).get();
    }
}
