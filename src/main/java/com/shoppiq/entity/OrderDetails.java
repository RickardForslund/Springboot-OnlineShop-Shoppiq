package com.shoppiq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private static Long id;

    @NotNull
    Item item;

    @Min(1)
    @NotEmpty
    int quantity;

    double price;

    public OrderDetails(Item item, @Min(1) @NotEmpty int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.price = item.price * quantity;
    }


    public OrderDetails() {

    }
}
