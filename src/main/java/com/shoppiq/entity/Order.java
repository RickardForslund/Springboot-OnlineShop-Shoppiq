package com.shoppiq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final LocalDateTime orderDate = LocalDateTime.now();
    @NotEmpty
    private User buyer;
    @NotEmpty
    private List<OrderDetails> orderDetails;

    public Order(User buyer, List<OrderDetails> orderDetails) {
        this.buyer = buyer;
        this.orderDetails = orderDetails;
    }
}
