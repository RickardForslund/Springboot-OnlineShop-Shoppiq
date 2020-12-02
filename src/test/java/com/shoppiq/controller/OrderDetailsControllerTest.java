package com.shoppiq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppiq.entity.Item;
import com.shoppiq.entity.OrderDetails;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderDetailsControllerTest {

    OrderDetails orderDetails;
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
        orderDetails = new OrderDetails(item, 3);

    }

    @Test
    void saveOrderDetails() throws Exception  {
        mockMvc.perform(post("/api/v1/orderdetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
              //  .andExpect(jsonPath("item").value(orderDetails.item()))
                .andExpect(jsonPath("quantity").value("3"))
                .andExpect(jsonPath("price").value(297));

    }

    @Test
    void findAllOrderDetails() {
    }

    @Test
    void findOrderDetailsById() {
    }
}