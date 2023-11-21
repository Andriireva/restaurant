package com.example.javapractice.restaurant.appevents;

import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.repository.IRestaurantRepository;
import com.example.javapractice.restaurant.repository.RestaurantRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RepositoryExample {

    private IRestaurantRepository iRestaurantRepository;

    public RepositoryExample(IRestaurantRepository iRestaurantRepository) {
        System.out.println("RepositoryExample is called");
        this.iRestaurantRepository = iRestaurantRepository;
    }

    // @EventListener(ApplicationReadyEvent.class) it marks that this method
    // must be executed ones when application is ready
    @EventListener(ApplicationReadyEvent.class)
    public void restaurantRepositoryPlayground() {
        Restaurant restaurant = iRestaurantRepository.get(5L);
        System.out.println(restaurant.toString());
    }
    //
//    public void examples() {
//        iRestaurantRepository.get(5L);
//    }
}
