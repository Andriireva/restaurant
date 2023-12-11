package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant get(Long id);
    Restaurant add(Restaurant restaurant);
    Restaurant update(Long id, Restaurant restaurant);
    void delete(Long id);


    List<Restaurant> getAll();
}
