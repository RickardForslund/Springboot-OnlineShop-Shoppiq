package com.shoppiq.controller;

import com.shoppiq.entity.OrderDetails;
import com.shoppiq.service.OrderDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orderdetails")
public class OrderDetailsController {

    private final OrderDetailsService orderdetailsService;

    public OrderDetailsController(OrderDetailsService orderdetailsService) {
        this.orderdetailsService = orderdetailsService;
    }

    @PostMapping
    public OrderDetails saveOrderDetails(@RequestBody OrderDetails orderdetails) {
        return orderdetailsService.saveOrderDetails(orderdetails);
    }

    @GetMapping
    public Iterable<OrderDetails> findAllOrderDetails() {
        return orderdetailsService.findAllOrderDetails();
    }

    @GetMapping("/{id}")
    public Optional<OrderDetails> findOrderDetailsById(@PathVariable Long id) {
        return orderdetailsService.findById(id);
    }

}
