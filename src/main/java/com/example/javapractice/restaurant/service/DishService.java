package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Dish;
import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.repository.IDishRepository;
import com.example.javapractice.restaurant.repository.IRestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Classes that are serve as a MAIN logical code
//
@Service
public class DishService implements IDishService {

    private IRestaurantRepository restaurantRepository;
    private IDishRepository dishRepository;

    @Override
    public Dish getOne(Long id) {
        return null;
    }

    @Override
    public Dish add(Long restaurantId, Dish dish) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
//        if (restaurant == null) {
//            throw new ParentResoruceNotFound();
//        }
        Dish addedDish = dishRepository.add(restaurantId, dish);

        return addedDish;
    }
}
