package com.shoppiq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    private String phone;

    @OneToOne(mappedBy = "resident", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Orders> orders;
    @JsonIgnore
    @OneToMany(mappedBy = "sellerId", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Item> items;

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty String email, String phone, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        address.setResident(this);
    }

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty String email, String phone, Address address, List<Item> itemsForSale) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        address.setResident(this);

        for (Item item:itemsForSale
        ) {item.setSellerId(this);
        }
        this.items = itemsForSale;
    }

    public void addOrder(Orders order) {
        if (order != null)
            orders.add(order);
    }

    public void addItem(Item item) {
        if (item != null){
            items.add(item);
            item.setSellerId(this);
        }

    }

    //TODO Remove if needed and replace in usage
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address.getStreetAddress() +
                ", orders=" + orders +
                ", items=" + items +
                '}';
    }
}

