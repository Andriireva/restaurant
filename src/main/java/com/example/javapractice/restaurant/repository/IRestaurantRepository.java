package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Restaurant;

import java.util.List;

public interface IRestaurantRepository {
    Restaurant get(Long id); // get dish by dish related table id
    Restaurant add(Restaurant restaurant);
    Restaurant update(Long id, Restaurant updatedRestaurant);
    void delete(Long id);
    List<Restaurant> getAll(); // do not use array []
}
