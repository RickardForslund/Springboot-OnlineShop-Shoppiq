//package com.shoppiq.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shoppiq.entity.*;
//import com.shoppiq.enums.Category;
//import com.shoppiq.repository.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.ws.rs.core.MediaType;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.matchesRegex;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class OrderControllerTest {
//
//    Orders order;
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    ItemRepository itemRepository;
//    @Autowired
//    OrderDetailsRepository orderDetailsRepository;
//    @Autowired
//    AddressRepository addressRepository;
//    @Autowired
//    UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        //TODO test doubles
////        List<OrderDetails> orderDetailsList = new ArrayList<>();
//        Item item = new Item("Mög", 99, 1, Category.CLOTHES, "En Mög med ett stols märke.");
//        itemRepository.save(item);
//        OrderDetails orderDetail = new OrderDetails(item.getId(), item.getName(), item.getPrice(), 1);
////        orderDetailsList.add(orderDetail);
//        orderDetailsRepository.save(orderDetail);
//        Address address = new Address("swe", "city", "street", "postal", "apNum", "c/o");
//        User user = new User("userName", "password", "e@mail.com", "0", address);
//        userRepository.save(user);
//        order = new Orders(user);
////        order.addOrderDetails(orderDetailsList);
//        orderRepository.save(order);
//    }
//
//    //    @Disabled
//    @Test
//    void saveOrder() throws Exception { //TODO kinda passes. Only works with id that might change. FIX
//        var jsonOrder = """
//                {
//                	"buyer":{
//                    "id": 1,
//                    "username": "user1",
//                    "password": "pass1",
//                    "email": "user1@email.com",
//                    "phone": "0701"
//                    }
//                }
//                                """;
//        mockMvc.perform(post("/api/v1/order")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonOrder))
////                .content(objectMapper.writeValueAsString(order)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(40));
////                .andExpect(jsonPath("buyer").value(order.getBuyer()));
////                .andExpect(jsonPath("buyer").value("{ \"id\": 1, \"username\": \"user1\", \"password\": \"pass1\", \"email\": \"user1@email.com\", \"phone\": \"0701\" }}"));
////                .andExpect(jsonPath("orderDate").value(order.getOrderDate()))
////                .andExpect(jsonPath("orderDetails").value(order.getOrderDetails()));
//    }
//
//    @Test
//    void getOrderById() throws Exception {
//        LocalDate dateNow = LocalDate.now();
//        mockMvc.perform(get("/api/v1/order/16"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(16))
//                .andExpect(jsonPath("orderDate").value(matchesRegex("^" + dateNow + ".*"))); //TODO replace date when in live environment
//    }
//
//    @Test
//    void getAllOrders() throws Exception {
//        mockMvc.perform(get("/api/v1/order"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(16))
//                .andExpect(jsonPath("$[0].orderDate").value(matchesRegex("^" + LocalDate.now() + ".*")))
//                .andExpect(jsonPath("$[0].buyer.id").value(1))
//                .andExpect(jsonPath("$[0].buyer.username").value("user1"))
//                .andExpect(jsonPath("$[0].buyer.password").value("pass1"))
//                .andExpect(jsonPath("$[0].buyer.email").value("user1@email.com"))
//                .andExpect(jsonPath("$[0].buyer.phone").value("0701"));
////                .andExpect(jsonPath("$[0].orderDetails").value("")); //TODO Make sure list is not empty?
//    }
//
//}