package com.shoppiq.entity;

import java.util.Objects;

public record OrderDetails (Long productId, String productName, double price, int quantity) {
    public OrderDetails {
        Objects.requireNonNull(productId);
        Objects.requireNonNull(productName);
    }
}
