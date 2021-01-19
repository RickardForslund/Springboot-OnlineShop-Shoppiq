//package com.shoppiq.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shoppiq.entity.Address;
//import com.shoppiq.entity.User;
//import com.shoppiq.repository.AddressRepository;
//import com.shoppiq.repository.UserRepository;
//import org.json.JSONException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    Address address;
//    User user;
//
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    AddressRepository addressRepository;
//
//    @BeforeEach
//    void setUp() throws JSONException {
//        address = new Address("Country 1", "City 1", "Street 1", "1", "1", "none");
//        user = new User("user11", "pass11", "user11@email.com", "0701", address);
//    }
//
//
//    @Test
//    void saveUser() throws Exception {  //TODO Gets wrong id when all tests runs at the same time
//        mockMvc.perform(post("/api/v1/user")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isOk())
////                .andExpect(jsonPath("id").value("30"))
//                .andExpect(jsonPath("username").value("user11"))
//                .andExpect(jsonPath("password").value("pass11"))
//                .andExpect(jsonPath("email").value("user11@email.com"))
//                .andExpect(jsonPath("phone").value("0701"))
////                .andExpect(jsonPath("$[*].id").value(31))
//                .andExpect(jsonPath("$[*].country").value("Country 1"))
//                .andExpect(jsonPath("$[*].city").value("City 1"))
//                .andExpect(jsonPath("$[*].streetAddress").value("Street 1"))
//                .andExpect(jsonPath("$[*].postalCode").value("1"))
//                .andExpect(jsonPath("$[*].apartmentNumber").value("1"))
//                .andExpect(jsonPath("$[*].co").value("none"));
//    }
//
//    @Test
//    void findAllUser() throws Exception {
//        mockMvc.perform(get("/api/v1/user"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0]id").value(1))
//                .andExpect(jsonPath("$[1]id").value(3))
//                .andExpect(jsonPath("$[2]id").value(5))
//                .andExpect(jsonPath("$[3]id").value(7))
//                .andExpect(jsonPath("$[4]id").value(9));
//    }
//
//    @Test
//    void findUserById() throws Exception {
//        mockMvc.perform(get("/api/v1/user/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value("1"))
//                .andExpect(jsonPath("username").value("user1"))
//                .andExpect(jsonPath("password").value("pass1"))
//                .andExpect(jsonPath("email").value("user1@email.com"))
//                .andExpect(jsonPath("phone").value("0701"));
//    }
//}