package com.shoppiq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Orders(User buyer) {
        this.buyer = buyer;
    }

    public void addOrderDetails(List<OrderDetails> orderDetails) {
        orderDetails.forEach(detail -> {
            if (detail != null) {
                this.orderDetails.add(detail);
                detail.setOrderId(this);
            }
        });
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        if (orderDetails != null) {
            this.orderDetails.add(orderDetails);
            orderDetails.setOrderId(this);
        }
    }

    public void setBuyer(User buyer) {
        if (buyer != null) {
            this.buyer = buyer;
            buyer.addOrder(this);
        }
    }
}
