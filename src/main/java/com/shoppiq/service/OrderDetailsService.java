package com.shoppiq.service;

import com.shoppiq.entity.OrderDetails;
import com.shoppiq.jms.service.RabbitMQSender;
import com.shoppiq.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderdetailsRepository;
    private final RabbitMQSender rabbitMQSender;

    public OrderDetailsService(OrderDetailsRepository orderdetailsRepository, RabbitMQSender rabbitMQSender) {
        this.orderdetailsRepository = orderdetailsRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public OrderDetails saveOrderDetails(OrderDetails orderdetails) {
        rabbitMQSender.send(orderdetails);
        return orderdetailsRepository.save(orderdetails);
    }

    public Iterable<OrderDetails> findAllOrderDetails() {
        return orderdetailsRepository.findAll();
    }

    public Optional<OrderDetails> findById(Long id) {
        return orderdetailsRepository.findById(id);
    }
}
