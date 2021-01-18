package com.shoppiq.service;

import com.shoppiq.entity.Address;
import com.shoppiq.jms.service.RabbitMQSender;
import com.shoppiq.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final RabbitMQSender rabbitMQSender;

    public AddressService(AddressRepository addressRepository, RabbitMQSender rabbitMQSender) {
        this.addressRepository = addressRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public Address saveAddress(Address address) {
        rabbitMQSender.send(address);
        return addressRepository.save(address);
    }

    public Iterable<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }
}
