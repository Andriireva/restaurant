package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Dish;
import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.exceptions.ResourceIsNotFoundException;
import com.example.javapractice.restaurant.exceptions.RestaurantException;
import com.example.javapractice.restaurant.repository.IDishRepository;
import com.example.javapractice.restaurant.repository.IRestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import java.time.Instant;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

// Dependencies of a class should be Mocked (provide fake classes that simulate behavior)
class RestaurantServiceTest {


    // Legacy-legacy way
//    private RestaurantService restaurantService = new RestaurantService(
//            new IRestaurantRepositoryMock(),
//            new IDishRepositoryMock()
//    );

    @InjectMocks // Used over test subject
    private RestaurantService restaurantService;

    @Mock // Mock dependency
    private IRestaurantRepository restaurantRepository;
    @Mock // Mock dependency
    private IDishRepository dishRepository;

    @BeforeEach // Junit annotation
    void setUp() {
        System.out.println("setUp is called");
        MockitoAnnotations.openMocks(this); // from Mockito library
        // on the background
        // restaurantRepository = new RestaurantRepositoryFakedClass()
        // dishRepository = new DishRepositoryFakedClass()
        // restaurantService = new RestaurantService(restaurantRepository, dishRepository)
    }

    @Test
    void getRestaurantsWithDishes() {
        Mockito.when(restaurantRepository.get(45L)).thenReturn(new Restaurant());
        Mockito.when(dishRepository.getAllByRestaurantId(45L)).thenReturn(
                Arrays.asList(
                        new Dish(),
                        new Dish()
                )
        );

        Restaurant restaurant = restaurantService.get(45L);

        assertNotNull(restaurant);
        assertNotNull(restaurant.getDishes());
        assertEquals(2, restaurant.getDishes().size());
    }

    @Test
    void getRestaurantsWithoutDishes() {
        Mockito.when(restaurantRepository.get(55L)).thenReturn(new Restaurant());

        Restaurant restaurant = restaurantService.get(55L);

        assertNotNull(restaurant);
        assertNotNull(restaurant.getDishes());
        assertEquals(0, restaurant.getDishes().size());
    }

    @Test
    void getRestaurantsThrowResourceNotFoundException() {
        ResourceIsNotFoundException resourceIsNotFoundException
                = assertThrowsExactly(ResourceIsNotFoundException.class, () -> restaurantService.get(55L));

        assertEquals("Restaurant with id 55 is not found", resourceIsNotFoundException.getMessage());
    }

    @Test
    void addSuccessWithoutDishes() {
        Restaurant restaurant = new Restaurant("New one", Instant.now(), "Yellow Forest street 22", 155, true, 101.54, null);
        Mockito.when(restaurantRepository.add(any()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Object firstArgument = invocation.getArgument(0);
                    Restaurant restaurantArg = (Restaurant)firstArgument;
                    restaurantArg.setId(4455L);
                    return restaurantArg;
                });
        Mockito.when(restaurantRepository.get(4455L)).thenReturn(restaurant);

        Restaurant addedRestaurant = restaurantService.add(restaurant);

        assertNotNull(addedRestaurant);
        assertNotNull(addedRestaurant.getId());
        Mockito.verify(restaurantRepository).add(any()); // verify restaurantRepository.add is execute 1 time with any variable
        Mockito.verify(dishRepository, never()).add(anyLong(), any()); // verify that  dishRepository.add did not executes at all
    }
}