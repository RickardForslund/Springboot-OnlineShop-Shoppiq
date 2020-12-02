package com.shoppiq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final LocalDateTime orderDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;

    public Orders(User buyer, List<OrderDetails> orderDetails) {
        this.buyer = buyer;
        this.orderDetails = orderDetails;
    }
}
