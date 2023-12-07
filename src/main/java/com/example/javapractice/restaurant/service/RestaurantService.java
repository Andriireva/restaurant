package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Dish;
import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.exceptions.ArgumentException;
import com.example.javapractice.restaurant.exceptions.ResourceIsNotFoundException;
import com.example.javapractice.restaurant.repository.IDishRepository;
import com.example.javapractice.restaurant.repository.IRestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IDishRepository dishRepository;

    // Facade. Try to look on a dissing approach called Facade
    public RestaurantService(IRestaurantRepository restaurantRepository,
                             IDishRepository dishRepository) {
        System.out.println("RestaurantService constructor is called");
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Restaurant get(Long id) {
        // get main information about a Restaurant
        // get all Dished by Restaurant id and set to an instance
        // What to do if Restaurant by id does not exist???
        // Method should throw exception
        Restaurant restaurant = restaurantRepository.get(id);
        if (restaurant == null) {
            throw new ResourceIsNotFoundException("Restaurant with id " + id + " is not found");
        }
        List<Dish> dishes = dishRepository.getAllByRestaurantId(id);
        restaurant.setDishes(dishes);
        return restaurant;
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        // add restaurant using restaurant repo
        // get restaurant id of returning type
        // add in a loop all restaurant.getDishes() using restaurant id
        if (restaurant.getCapacity() < 0) {
            throw new ArgumentException("restaurant capacity must not be negative");
        }

        Restaurant addedRestaurant = restaurantRepository.add(restaurant);
        Long addedRestaurantId = addedRestaurant.getId();
        if (restaurant.getDishes() != null) {
            restaurant.getDishes().forEach(dish -> dishRepository.add(addedRestaurantId, dish));
        }

        return get(addedRestaurantId);
    }

    @Override
    public Restaurant update(Long id, Restaurant restaurant) {
        // what to do when update restaurant.getDishes() ????
        // the answer: you should replace the dishes in case restaurant.getDishes() not empty

        // option 1:
        // Steps:
        // Validate, that Restaurant by id exist???
        // restaurantRepository.update(id, restaurant);
        // dishRepository.deleteAllByRestaurantId(id)
        // restaurant.getDishes().forEach(dish -> dishRepository.add(id, dish))

        // option 2:
        // iterate over the restaurant.getDishes() and check dish has id or not
        // if exist call dishRepository.update(dishId, dish)

        return null;
    }

    @Override
    public void delete(Long id) {
        get(id);
        dishRepository.deleteAllByRestaurantId(id);
        restaurantRepository.delete(id);
    }
}
