package com.shoppiq.entity;

import java.util.Objects;

public record Address(String country, String city, String streetAddress, String postalCode, String apartmentNumber, String co) {
    public Address {
        Objects.requireNonNull(country);
        Objects.requireNonNull(city);
        Objects.requireNonNull(streetAddress);
        Objects.requireNonNull(postalCode);
    }
}
