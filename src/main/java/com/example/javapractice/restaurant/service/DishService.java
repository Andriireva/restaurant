package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Dish;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Classes that are serve as a MAIN logical code
//
@Service
public class DishService implements IDishService {
    @Override
    public Dish getOne(Long id) {
        return null;
    }

    @Override
    public Dish add(Long restaurantId, Dish dish) {
        return null;
    }
}
