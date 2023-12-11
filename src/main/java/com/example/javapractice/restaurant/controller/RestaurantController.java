package com.example.javapractice.restaurant.controller;


import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.service.IRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/restaurants")
@RestController
public class RestaurantController {
    private final IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    // this method should be called when GET /restaurant/api/restaurants/<4343>
    @GetMapping(path = "/{id}")
    public Restaurant getOne(@PathVariable Long id) {
        System.out.println("RestaurantController.getOne is called");
        return restaurantService.get(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant add(@RequestBody Restaurant restaurant) {
        System.out.println("RestaurantController.add is called");
        return restaurantService.add(restaurant);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant update(@PathVariable Long id,
                             @RequestBody Restaurant restaurant) {
        System.out.println("RestaurantController.add is called");
        return restaurantService.update(id, restaurant);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    @GetMapping(path = "/path")
    public Restaurant anotherDumyRe() {
        return new Restaurant("Restaurant for test", null, null, null, null, null, null);
    }

    @GetMapping
    public List<Restaurant> getALl() {
        return restaurantService.getAll();
    }

}
