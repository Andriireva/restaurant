package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Dish;

public interface IDishService {
    Dish getOne(Long id);

    Dish add(Long restaurantId, Dish dish);
}
