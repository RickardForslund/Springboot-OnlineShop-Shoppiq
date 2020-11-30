package com.shoppiq.entity;

import com.shoppiq.enums.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Item {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @NotEmpty
    String name;

    @Min(1)
    @NotEmpty
    double price;

    @Min(0)
    @NotNull
    int quantity;

    String description;

    @NotNull
    Category category;




    public Item(@NotEmpty String name, @Min(1) @NotEmpty double price, @Min(0) @NotNull int quantity,Category category, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }


}
