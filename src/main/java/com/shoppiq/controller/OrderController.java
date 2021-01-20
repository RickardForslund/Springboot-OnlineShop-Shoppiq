package com.shoppiq.controller;

import com.shoppiq.entity.Item;
import com.shoppiq.entity.OrderDetails;
import com.shoppiq.entity.Orders;
import com.shoppiq.entity.User;
import com.shoppiq.service.ItemService;
import com.shoppiq.service.OrderService;
import com.shoppiq.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Controller
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ItemService itemService;

    public OrderController(OrderService orderService, UserService userService, ItemService itemService) {
        this.orderService = orderService;
        this.userService = userService;
        this.itemService = itemService;
    }

/*    @PostMapping
    public Orders saveOrder(@RequestBody Orders order) {
        return orderService.saveOrder(order);
    }*/

    @PostMapping("/add")
    public String saveOrder(String buyer, OrderDetails orderDetails, BindingResult result, Model model) {
        if (result.hasErrors())
            return "error";
        User user = userService.findByUsername(buyer).get();
        Orders orders = new Orders(user);
        orders.addOrderDetails(orderDetails);
        orderService.saveOrder(orders);
        Item item = itemService.findById(orderDetails.getItemId()).get();
        item.setQuantity(item.getQuantity()-orderDetails.getQuantity());
        model.addAttribute("buy",orders);
        return "buy";
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
