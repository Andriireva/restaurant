package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Restaurant;

public interface IRestaurantService {
    Restaurant get(Long id);
    Restaurant add(Restaurant restaurant);
    Restaurant update(Long id, Restaurant restaurant);
    void delete(Long id);


}
