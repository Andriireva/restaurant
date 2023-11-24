package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Dish;

import java.util.List;

public interface IDishRepository {
    Dish get(Long id); // get dish by dish related table id
    Dish add(Long restaurantId, Dish dish);
    Dish update(Long id, Dish dishToBeAdded);
    void delete(Long id);
    List<Dish> getAll(); // do not use array []
}
