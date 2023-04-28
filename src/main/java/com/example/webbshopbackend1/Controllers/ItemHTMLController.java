package com.example.webbshopbackend1.Controllers;

import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Repos.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/itemHTML")
public class ItemHTMLController {

    private final ItemRepo itemRepo;

    public ItemHTMLController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }


    @RequestMapping("/getAll")
    public String items(Model model){ //Model är vår "plastpåse"
        List<Item> itemList = itemRepo.findAll();
        model.addAttribute("items", itemList);
        return "items";
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

}
