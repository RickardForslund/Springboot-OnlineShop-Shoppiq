package com.shoppiq.service;

import com.shoppiq.entity.OrderDetails;
import com.shoppiq.entity.Orders;
import com.shoppiq.repository.OrderDetailsRepository;
import com.shoppiq.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    public Iterable<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void addOrderDetail(Long id, OrderDetails orderDetails) {
        var order = orderRepository.findById(id);
        order.ifPresent(orders -> orders.addOrderDetails(orderDetails));
    }

    public void addOrderDetail(Long orderId, Long orderDetailsId) {
        var order = orderRepository.findById(orderId);
        var orderDetails = orderDetailsRepository.findById(orderDetailsId);
        if (orderDetails.isPresent() && order.isPresent())
            order.get().addOrderDetails(orderDetails.get());
    }
}
