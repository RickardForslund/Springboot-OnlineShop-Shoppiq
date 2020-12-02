package com.shoppiq.service;

import com.shoppiq.entity.Orders;
import com.shoppiq.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    public Iterable<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }
}
