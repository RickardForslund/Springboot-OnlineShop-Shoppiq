package com.shoppiq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppiq.entity.Address;
import com.shoppiq.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    Address address;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        address = new Address("Country 1", "City 1", "Street 1", "1", "1", "none");
        addressRepository.save(address);
    }


    @Test
    void saveAddress()  throws Exception {
        mockMvc.perform(post("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value("Country 1"))
                .andExpect(jsonPath("city").value("City 1"))
                .andExpect(jsonPath("streetAddress").value("Street 1"))
                .andExpect(jsonPath("postalCode").value("1"))
                .andExpect(jsonPath("apartmentNumber").value("1"))
                .andExpect(jsonPath("co").value("none"));

    }

    @Test
    void findAllAddress()  throws Exception {
        mockMvc.perform(get("/api/v1/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].country").value("Country 1"))
                .andExpect(jsonPath("$[0].city").value("City 1"))
                .andExpect(jsonPath("$[0].streetAddress").value("Street 1"))
                .andExpect(jsonPath("$[0].postalCode").value("1"))
                .andExpect(jsonPath("$[0].apartmentNumber").value("1"))
                .andExpect(jsonPath("$[0].co").value("none"))
                .andExpect(jsonPath("$[1].country").value("Country 2"));
    }

    @Test
    void findAddressById()  throws Exception {
        mockMvc.perform(get("/api/v1/address/6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value("Country 3"))
                .andExpect(jsonPath("city").value("City 3"))
                .andExpect(jsonPath("streetAddress").value("Street 3"))
                .andExpect(jsonPath("postalCode").value("3"))
                .andExpect(jsonPath("apartmentNumber").value("3"))
                .andExpect(jsonPath("co").value("none"));
    }
}