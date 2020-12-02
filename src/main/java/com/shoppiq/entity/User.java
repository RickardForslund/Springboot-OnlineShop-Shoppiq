package com.shoppiq.entity;

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

    @OneToOne(mappedBy = "resident", cascade = CascadeType.PERSIST)
    private Address address;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST)
    private List<Orders> orders;
    @OneToMany(mappedBy = "sellerId", cascade = CascadeType.PERSIST)
    private List<Item> items;

    public User(@NotEmpty String username, @NotEmpty String password, @NotEmpty String email, String phone, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

}

