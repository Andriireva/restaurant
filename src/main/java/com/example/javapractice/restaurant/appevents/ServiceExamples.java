package com.example.javapractice.restaurant.appevents;

import com.example.javapractice.restaurant.domain.Dish;
import com.example.javapractice.restaurant.domain.DishType;
import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.service.IRestaurantService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

//@Component
public class ServiceExamples {

    private IRestaurantService restaurantService;

    public ServiceExamples(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void restaurantServicePlayground() {
        Restaurant restaurant = restaurantService.get(5L);
        System.out.println(restaurant.getDishes() != null && restaurant.getDishes().isEmpty());

//        List<Dish> dishList = Arrays.asList(
//                new Dish("Choco-peanut", 33d, 4, "2 types", DishType.DESERTS),
//                new Dish("Vanile", 13d, 3, "1", DishType.DESERTS),
//                new Dish("Juice apple", 4.5d, 1, "apple juice", DishType.DRINK)
//        );
//        Restaurant restaurantToBeAdded =
//                new Restaurant("Perfect ice-cream cafe", Instant.now(), "Purple strea", 10, true, 14.5, dishList);
//
//        Restaurant addedRestaurantWithDishes = restaurantService.add(restaurantToBeAdded);
        System.out.println("=== newly added dish ===");
//        System.out.println(addedRestaurantWithDishes);

        restaurantService.delete(15L);
    }
}
