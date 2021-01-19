package com.shoppiq.service;

import com.shoppiq.entity.Orders;
import com.shoppiq.jms.service.RabbitMQSender;
import com.shoppiq.repository.OrderDetailsRepository;
import com.shoppiq.repository.OrderRepository;
import com.shoppiq.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    //TODO Make a new controller to handle cross entity interactions?
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserRepository userRepository;
    private final RabbitMQSender rabbitMQSender;

    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, UserRepository userRepository, RabbitMQSender rabbitMQSender) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.userRepository = userRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public Orders saveOrder(Orders order) {
        rabbitMQSender.send(order);
        return orderRepository.save(order);
    }

    public Iterable<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void addOrderDetail(Long orderId, Long orderDetailsId) {
        var order = orderRepository.findById(orderId);
        var orderDetails = orderDetailsRepository.findById(orderDetailsId);
        if (orderDetails.isPresent() && order.isPresent())
            order.get().addOrderDetails(orderDetails.get());
    }

    public void setBuyer(Long orderId, Long buyerId) {
        var order = orderRepository.findById(orderId);
        var buyer = userRepository.findById(buyerId);
        if (order.isPresent() && buyer.isPresent()) {
            order.get().setBuyer(buyer.get());
        }
    }

}
