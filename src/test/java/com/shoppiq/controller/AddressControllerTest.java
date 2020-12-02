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
        address = new Address("Sweden","Gothenburg", "Krongatan 10", "63042","10","kroingatan11");
        addressRepository.save(address);
        addressRepository.save(address);
    }


    @Test
    void saveAddress()  throws Exception {
        mockMvc.perform(post("/api/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value("Sweden"))
                .andExpect(jsonPath("city").value("Gothenburg"))
                .andExpect(jsonPath("streetAddress").value("Krongatan 10"))
                .andExpect(jsonPath("postalCode").value("63042"))
                .andExpect(jsonPath("apartmentNumber").value("10"))
                .andExpect(jsonPath("co").value("kroingatan11"));

    }

    @Test
    void findAllAddress()  throws Exception {
        mockMvc.perform(get("/api/v1/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].country").value("Sweden"))
                .andExpect(jsonPath("$[0].city").value("Gothenburg"))
                .andExpect(jsonPath("$[0].streetAddress").value("Krongatan 10"))
                .andExpect(jsonPath("$[0].postalCode").value("63042"))
                .andExpect(jsonPath("$[0].apartmentNumber").value("10"))
                .andExpect(jsonPath("$[0].co").value("kroingatan11"));
    }

    @Test
    void findAddressById()  throws Exception {
        mockMvc.perform(get("/api/v1/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value("Sweden"))
                .andExpect(jsonPath("city").value("Gothenburg"))
                .andExpect(jsonPath("streetAddress").value("Krongatan 10"))
                .andExpect(jsonPath("postalCode").value("63042"))
                .andExpect(jsonPath("apartmentNumber").value("10"))
                .andExpect(jsonPath("co").value("kroingatan11"));
    }
}