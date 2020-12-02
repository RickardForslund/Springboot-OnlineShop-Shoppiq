package com.shoppiq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppiq.entity.Address;
import com.shoppiq.entity.Item;
import com.shoppiq.entity.User;
import com.shoppiq.enums.Category;
import com.shoppiq.repository.AddressRepository;
import com.shoppiq.repository.ItemRepository;
import com.shoppiq.repository.UserRepository;
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
class UserControllerTest {

    User user;
    User user2;
    Address address;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    //TODO: A problem with the adresses in BeforeEach is currently causing all tests to fail
    @BeforeEach
    void setUp() {
        address = new Address("Sweden","Gothenburg", "Krongatan 10", "63042","10","Rickard Forslund");
        addressRepository.save(address);
        user = new User("Oniaon", "password123", "natalie@gmail.com","123456677889", address);
        user2 = new User("coolkid", "awesomesauce123", "poopoo@gmail.com","987657439", address);
        userRepository.save(user);
        userRepository.save(user2);
    }

    @Test
    void saveUser() throws Exception {

        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("Oniaon"))
                .andExpect(jsonPath("password").value("password123"))
                .andExpect(jsonPath("email").value("natalie@gmail.com"));


    }

    @Test
    void findAllUser() throws Exception {
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Oniaon"))
                .andExpect(jsonPath("$[0].password").value("password123"))
                .andExpect(jsonPath("$[0].email").value("natalie@gmail.com"))
                .andExpect(jsonPath("$[1].username").value("coolkid"))
                .andExpect(jsonPath("$[1].password").value("awesomesauce123"))
                .andExpect(jsonPath("$[1].email").value("poopoo@gmail.com"));


    }


    @Test
    void findUserById() throws Exception {
        mockMvc.perform(post("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("Oniaon"))
                .andExpect(jsonPath("password").value("password123"))
                .andExpect(jsonPath("email").value("natalie@gmail.com"));
    }
}