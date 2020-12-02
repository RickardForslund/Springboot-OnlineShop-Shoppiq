package com.shoppiq.entity;

import com.shoppiq.enums.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Item {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @NotEmpty
    private String name;
    @Min(1)
    @NotEmpty
    private double price;
    @Min(0)
    @NotNull
    private int quantity;
    private String description;
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    User sellerId;

    public Item(@NotEmpty String name, @Min(1) @NotEmpty double price, @Min(0) @NotNull int quantity, Category category, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                quantity == item.quantity &&
                Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                category == item.category;
    }
}
