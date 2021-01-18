package com.shoppiq.controller;

import com.shoppiq.entity.OrderDetails;
import com.shoppiq.service.OrderDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
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

    @PostMapping("/add")
    public String saveOrderDetails(@ModelAttribute("orderDetails") OrderDetails orderDetails, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        orderdetailsService.saveOrderDetails(orderDetails);
        model.addAttribute("orderDetails",orderDetails);
        return "orderDetails";
    }


    @GetMapping("/view/{id}")
    public String showOrderDetailsById(Model model, @PathVariable Long id) {
        OrderDetails orderDetails = null;

        try {
            orderDetails = orderdetailsService.findById(id).get();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Item not found");
        }

        model.addAttribute("orderDetails",orderDetails);
        return "orderDetails";
    }

}
