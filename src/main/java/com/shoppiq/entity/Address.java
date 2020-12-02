package com.shoppiq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String country, city, streetAddress, postalCode;
    private String apartmentNumber, co;

    @OneToOne
    User resident;

    public Address(@NotEmpty String country, @NotEmpty String city, @NotEmpty String streetAddress, @NotEmpty String postalCode, String apartmentNumber, String co) {
        this.country = country;
        this.city = city;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.apartmentNumber = apartmentNumber;
        this.co = co;
    }
}
