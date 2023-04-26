package com.example.webbshopbackend1;

import com.example.webbshopbackend1.Models.Item;
import com.example.webbshopbackend1.Repos.ItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemRepo itemRepo;

    @BeforeEach
    void beforeTest(){
        Item item1 = new Item(1L, "Joggers", 599);
        Item item2 = new Item(2L, "Black sneakers", 859);
        Item item3 = new Item(3L, "Spongebob T-shirt", 150);

        when(itemRepo.findById(1L)).thenReturn(Optional.of(item1));
        when(itemRepo.findById(2L)).thenReturn(Optional.of(item2));
        when(itemRepo.findById(3L)).thenReturn(Optional.of(item3));
        when(itemRepo.findAll()).thenReturn(Arrays.asList(item1, item2, item3));
    }

    @Test
    void getAllTest() throws Exception {
        this.mvc.perform(get("/items/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Joggers\",\"price\":599}," +
                        "{\"id\":2,\"name\":\"Blue sneakers\",\"price\":859}," +
                        "{\"id\":3,\"name\":\"Spongebob t-shirt\",\"price\":150}]"));
    }

    @Test
    void getItemByIdTest(){

    }
/*
    @Test
    void addItemTest(){
        this.mvc.perform(get("/items/add"))
                .andExpect(status().isOk())
                .andExpect(content().json())
    }*/

    /*

    @RequestMapping("/getAll")
    public List<Item> getItems(){
        return itemRepo.findAll();
    }
    @RequestMapping("/getByItemId/{id}")
    public Item getItems(@PathVariable Long id){
        return itemRepo.findById(id).get();
    }

    //curl http://localhost:8080/items/add -H "Content-Type:application/json" -d "{\"name\":\"Lola-shirt\", \"price\":1745}" -v
    @PostMapping("/add")
    public Item addItem(@RequestBody Item item){
        itemRepo.save(item);
        return itemRepo.findById(item.getId()).get();
    }*/
}
