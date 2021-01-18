package com.shoppiq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Orders orderId;

//    public OrderDetails(Item item, @Min(1) @NotEmpty int quantity) {
    public OrderDetails(Long itemId, String itemName, double price, @Min(1) @NotEmpty int quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price * quantity;
    }

    public OrderDetails(@NotEmpty Item item, @Min(1) @NotEmpty int quantity) {
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.quantity = quantity;
        this.price = item.getPrice() * quantity;
    }

    public OrderDetails(@NotEmpty Item item, @Min(1) @NotEmpty String quantity) {
        this.itemId = item.getId();
        this.itemName = item.getName();
        this.quantity = Integer.parseInt(quantity);
        this.price = item.getPrice() * Integer.parseInt(quantity);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderId=" + orderId.getId() +
                '}';
    }
}
