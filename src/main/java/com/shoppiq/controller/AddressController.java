package com.shoppiq.controller;

import com.shoppiq.entity.Address;
import com.shoppiq.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @GetMapping
    public Iterable<Address> findAllAddress() {
        return addressService.findAllAddress();
    }

    @GetMapping("/{id}")
    public Optional<Address> findAddressById(@PathVariable Long id) {
        return addressService.findById(id);
    }
}
