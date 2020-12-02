package com.shoppiq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppiq.entity.Item;
import com.shoppiq.entity.OrderDetails;
import com.shoppiq.enums.Category;
import com.shoppiq.repository.ItemRepository;
import com.shoppiq.repository.OrderDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderDetailsControllerTest {

    OrderDetails orderDetails;
    OrderDetails orderDetails2;
    Item item;
    Item item2;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @BeforeEach
    void setUp() {
        item = new Item("Stolströja", 99, 1, Category.CLOTHES, "En tröja med ett stols märke.");
        orderDetails = new OrderDetails(item, 3);
        orderDetailsRepository.save(orderDetails);
        item2 = new Item("Cat Toy", 50, 1, Category.TOYS, "A feather tied with string to a stick. Cats love it!");
        orderDetails2 = new OrderDetails(item2, 5);
        orderDetailsRepository.save(orderDetails2);
    }

    @Test
    void saveOrderDetails() throws Exception  {
        mockMvc.perform(post("/api/v1/orderdetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("itemId").value(item.getId()))
                .andExpect(jsonPath("itemName").value("Stolströja"))
                .andExpect(jsonPath("quantity").value("3"))
                .andExpect(jsonPath("price").value(297));

    }

    @Test
    void findAllOrderDetails() throws Exception {
        mockMvc.perform(get("/api/v1/orderdetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].itemName").value("Stolströja"))
                .andExpect(jsonPath("$[0].price").value(297))
                .andExpect(jsonPath("$[0].quantity").value(3))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].itemName").value("Cat Toy"))
                .andExpect(jsonPath("$[1].price").value(250))
                .andExpect(jsonPath("$[1].quantity").value(5));

    }

    //TODO: Fix this! Why doesnt it work?
    @Test
    void findOrderDetailsById() throws Exception {
        mockMvc.perform(get("/api/v1/orderdetails/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].itemName").value("Cat Toy"))
                .andExpect(jsonPath("$[1].price").value(250))
                .andExpect(jsonPath("$[1].quantity").value(5));
    }
}