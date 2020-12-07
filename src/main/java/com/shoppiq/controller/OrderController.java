package com.shoppiq.controller;

import com.shoppiq.entity.Orders;
import com.shoppiq.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders saveOrder(@RequestBody Orders order) {
        return orderService.saveOrder(order);
    }

    @GetMapping
    public Iterable<Orders> findAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Orders> findOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping("/{orderId}/detail/{detailId}")
    public void addOrderDetails(@PathVariable Long orderId, @PathVariable Long detailId) {
        orderService.addOrderDetail(orderId, detailId);
    }

    @PostMapping("/{orderId}/buyer/{buyerId}")
    public void setBuyer(@PathVariable Long orderId, @PathVariable Long buyerId) {
        orderService.setBuyer(orderId, buyerId);
    }

}
