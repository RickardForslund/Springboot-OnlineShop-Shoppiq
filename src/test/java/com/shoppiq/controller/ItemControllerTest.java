//package com.shoppiq.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shoppiq.entity.Item;
//import com.shoppiq.enums.Category;
//import com.shoppiq.repository.ItemRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ItemControllerTest {
//
//
//    Item item;
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    ItemRepository itemRepository;
//
//
//    @BeforeEach
//    void setUp() {
//        item = new Item("TV", 1999.00, 5, Category.ELECTRONICS, "Very descriptive text");
//        itemRepository.save(item);
//        itemRepository.save(item);
//    }
//
//    @Test
//    void saveItem() throws Exception {
//        mockMvc.perform(post("/api/v1/item")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(item)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("name").value("TV"))
//                .andExpect(jsonPath("price").value(1999.00))
//                .andExpect(jsonPath("quantity").value(5))
//                .andExpect(jsonPath("category").value("ELECTRONICS"))
//                .andExpect(jsonPath("description").value("Very descriptive text"));
//    }
//
//    @Test
//    void findAllItems() throws Exception {
//        mockMvc.perform(get("/api/v1/item"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(11))
//                .andExpect(jsonPath("$[1].id").value(12))
//                .andExpect(jsonPath("$[2].id").value(13))
//                .andExpect(jsonPath("$[3].id").value(14))
//                .andExpect(jsonPath("$[4].id").value(15));
//    }
//
//    @Test
//    void findItemById() throws Exception {
//        mockMvc.perform(get("/api/v1/item/13"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(13))
//                .andExpect(jsonPath("name").value("Jacket"))
//                .andExpect(jsonPath("price").value(499.0))
//                .andExpect(jsonPath("quantity").value(3))
//                .andExpect(jsonPath("category").value("CLOTHES"))
//                .andExpect(jsonPath("description").value("Very descriptive text"));
//    }
//
//}