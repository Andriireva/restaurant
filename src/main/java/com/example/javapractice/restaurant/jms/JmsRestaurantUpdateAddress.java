package com.example.javapractice.restaurant.jms;

public class JmsRestaurantUpdateAddress {
    private Long id;
    private String newAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
}
