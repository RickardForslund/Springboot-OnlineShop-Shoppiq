package com.shoppiq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppiq.entity.Item;
import com.shoppiq.enums.Category;
import com.shoppiq.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {


    Item item;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ItemRepository itemRepository;


    @BeforeEach
    void setUp() {
        item = new Item("Stolströja", 99, 1, Category.CLOTHES, "En tröja med ett stols märke.");
        itemRepository.save(item);
        itemRepository.save(item);
    }

    @Test
    void saveItem() throws Exception {
        mockMvc.perform(post("/api/v1/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
        .andExpect(jsonPath("name").value("Stolströja"))
        .andExpect(jsonPath("price").value(99))
        .andExpect(jsonPath("quantity").value(1))
        .andExpect(jsonPath("category").value("CLOTHES"))
        .andExpect(jsonPath("description").value("En tröja med ett stols märke."));
    }

    @Test
    void findAllItems()  throws Exception{
        mockMvc.perform(get("/api/v1/item"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Stolströja"))
                .andExpect(jsonPath("$[0].price").value(99))
                .andExpect(jsonPath("$[0].quantity").value(1))
                .andExpect(jsonPath("$[0].category").value("CLOTHES"))
                .andExpect(jsonPath("$[0].description").value("En tröja med ett stols märke."));


    }

    @Test
    void findItemById() throws Exception{
        mockMvc.perform(get("/api/v1/item/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Stolströja"))
                .andExpect(jsonPath("price").value(99))
                .andExpect(jsonPath("quantity").value(1))
                .andExpect(jsonPath("category").value("CLOTHES"))
                .andExpect(jsonPath("description").value("En tröja med ett stols märke."));
    }

}