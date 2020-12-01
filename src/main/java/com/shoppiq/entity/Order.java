package com.shoppiq.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record Order (User buyer, LocalDateTime orderDate, List<OrderDetails> orderDetails) {
    public Order {
        Objects.requireNonNull(buyer);
        Objects.requireNonNull(orderDate);
        Objects.requireNonNull(orderDetails);
    }
}
