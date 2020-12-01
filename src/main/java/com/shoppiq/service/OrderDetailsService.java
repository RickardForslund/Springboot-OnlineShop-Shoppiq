package com.shoppiq.service;

import com.shoppiq.entity.OrderDetails;
import com.shoppiq.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderdetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderdetailsRepository) {
        this.orderdetailsRepository = orderdetailsRepository;
    }

    public OrderDetails saveOrderDetails(OrderDetails orderdetails) {
        return orderdetailsRepository.save(orderdetails);
    }

    public Iterable<OrderDetails> findAllOrderDetails() {
        return orderdetailsRepository.findAll();
    }

    public Optional<OrderDetails> findById(Long id) {
        return orderdetailsRepository.findById(id);
    }
}
