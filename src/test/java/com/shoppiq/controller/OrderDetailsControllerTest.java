//package com.shoppiq.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shoppiq.entity.Item;
//import com.shoppiq.entity.OrderDetails;
//import com.shoppiq.enums.Category;
//import com.shoppiq.repository.ItemRepository;
//import com.shoppiq.repository.OrderDetailsRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class OrderDetailsControllerTest {
//
//    OrderDetails orderDetails;
//    Item item;
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    OrderDetailsRepository orderDetailsRepository;
//
//
//    @BeforeEach
//    void setUp() {
//        item = new Item("TV", 1999.00, 3, Category.ELECTRONICS, "Very descriptive text");
//        orderDetails = new OrderDetails(100L, item.getName(),item.getPrice(), 1);
//    }
//
//
//    //TODO: This test returns 400 instead of 200 ok
//    @Test
//    void saveOrderDetails() throws Exception  {
//        mockMvc.perform(post("/api/v1/orderdetails")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(orderDetails)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(25))
//                .andExpect(jsonPath("itemId").value(100))
//                .andExpect(jsonPath("itemName").value("TV"))
//                .andExpect(jsonPath("price").value(1999.0))
//                .andExpect(jsonPath("quantity").value(1));
//
//    }
//
//    @Test
//    void findAllOrderDetails() throws Exception {
//        mockMvc.perform(get("/api/v1/orderdetails"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(17))
//                .andExpect(jsonPath("$[0].itemName").value("TV"))
//                .andExpect(jsonPath("$[0].price").value(1999))
//                .andExpect(jsonPath("$[0].quantity").value(1))
//                .andExpect(jsonPath("$[1].id").value(18))
//                .andExpect(jsonPath("$[1].itemName").value("VHS Player"))
//                .andExpect(jsonPath("$[1].price").value(99.0))
//                .andExpect(jsonPath("$[1].quantity").value(1));
//
//    }
//
//
//    @Test
//    void findOrderDetailsById() throws Exception {
//        mockMvc.perform(get("/api/v1/orderdetails/17"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("itemName").value("TV"))
//                .andExpect(jsonPath("price").value(1999.0))
//                .andExpect(jsonPath("quantity").value(1));
//    }
//}