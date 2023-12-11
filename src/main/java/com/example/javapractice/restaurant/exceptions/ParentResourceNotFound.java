package com.example.javapractice.restaurant.exceptions;

public class ParentResourceNotFound extends RestaurantException {
    public ParentResourceNotFound(String message) {
        super(message);
    }
}
