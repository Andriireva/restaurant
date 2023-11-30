package com.example.javapractice.restaurant.controller;

// Controller is class that act as mediator between this application
// and "other"

import com.example.javapractice.restaurant.domain.Dish;
import com.example.javapractice.restaurant.service.IDishService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/")
@RestController
public class DishController {

    private final IDishService dishService;

    public DishController(IDishService dishService) {
        this.dishService = dishService;
    }

    // Both works
    // GET /api/restaurants/<id>/dishes/<id>
    // GET /api/dishes/id
    @GetMapping(path = "/dishes/{id}")
    public Dish getOne(@PathVariable Long id) {
        return dishService.getOne(id);
    }


    // POST /api/restaurants/<id>/dishes
    @PostMapping(path = "/restaurants/{restaurantId}/dishes")
    public Dish addOne(@PathVariable Long restaurantId,
                       @RequestBody Dish dish) {
        System.out.println("DishController.addOne: restaurantId=" + restaurantId + ", dish " + dish);
        return dishService.add(restaurantId, dish);
    }
}
