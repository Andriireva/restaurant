package com.example.javapractice.restaurant.appevents;

import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.repository.IRestaurantRepository;
import com.example.javapractice.restaurant.repository.RestaurantRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

//@Component
public class RepositoryExample {

    private IRestaurantRepository iRestaurantRepository;

    public RepositoryExample(IRestaurantRepository iRestaurantRepository) {
        System.out.println("RepositoryExample is called");
        this.iRestaurantRepository = iRestaurantRepository;
    }

    // @EventListener(ApplicationReadyEvent.class) it marks that this method
    // must be executed ones when application is ready
//    @EventListener(ApplicationReadyEvent.class)
    public void restaurantRepositoryPlayground() {
        Restaurant restaurant = iRestaurantRepository.get(5000L);
        System.out.println(restaurant.toString());

        // I want to add a new Restauratt ot our storage ( data base)???
        Restaurant restaurantToBeAddedToDataBase =
                new Restaurant("New one", Instant.now(), "Yellow Forest street 22", 155, true, 101.54, null);
//        List<Restaurant> restaurantsToBeAdded = Arrays.asList(
//                new Restaurant(),
//                new Restaurant(),
//                new Restaurant());
//        restaurantsToBeAdded.forEach(rest -> iRestaurantRepository.add(rest));
        Restaurant justAddedRestaurant = iRestaurantRepository.add(restaurantToBeAddedToDataBase);
        System.out.println("justAddedRestaurant: " + justAddedRestaurant.toString());
    }
    //
//    public void examples() {
//        iRestaurantRepository.get(5L);
//    }
}
