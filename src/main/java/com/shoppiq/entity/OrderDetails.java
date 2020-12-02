package com.shoppiq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    Long itemId;
    String itemName;
    @Min(1)
    @NotEmpty
    int quantity;
    double price;

    @ManyToOne()
    Order orderId;

    public OrderDetails(Item item, @Min(1) @NotEmpty int quantity) {
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.quantity = quantity;
        this.price = item.getPrice() * quantity;
    }
}
