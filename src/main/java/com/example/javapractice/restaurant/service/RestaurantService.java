package com.example.javapractice.restaurant.service;

import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.exceptions.ArgumentException;
import com.example.javapractice.restaurant.exceptions.ResourceIsNotFoundException;
import com.example.javapractice.restaurant.repository.*;
import com.example.javapractice.restaurant.repository.RestaurantRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IDishRepository dishRepository;
    private final RestaurantRepo restaurantRepo;
    private final DishRepo dishRepo;

//    @Autowired
    // Facade. Try to look on a dissing approach called Facade
    public RestaurantService(IRestaurantRepository restaurantRepository,
                             IDishRepository dishRepository,
                             RestaurantRepo restaurantRepo,
                             DishRepo dishRepo) {
        this.dishRepo = dishRepo;
        System.out.println("RestaurantService constructor is called ONE");
        System.out.println("RestaurantRepo " + restaurantRepo.toString());

        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.restaurantRepo = restaurantRepo;
//        this.restaurantRepo = new SomeMagicImplOfRestaurantRepo();
    }


    @Override
    public Restaurant get(Long id) {
        // get main information about a Restaurant
        // get all Dished by Restaurant id and set to an instance
        // What to do if Restaurant by id does not exist???
        // Method should throw exception


        // OLD ONE
//        Restaurant restaurant = restaurantRepository.get(id);
//        if (restaurant == null) {
//            throw new ResourceIsNotFoundException("Restaurant with id " + id + " is not found");
//        }
//        List<Dish> dishes = dishRepository.getAllByRestaurantId(id);
//        restaurant.setDishes(dishes);
//        return restaurant;


        // findById: create SQL like select id, name, start_work_date, ...... from @Table.name where id = :id

        return restaurantRepo.findById(id)
                .orElseThrow(() -> new ResourceIsNotFoundException("Restaurant with id " + id + " is not found"));

    }

    @Override
    @Transactional
    public Restaurant add(Restaurant restaurant) {
        // add restaurant using restaurant repo
        // get restaurant id of returning type
        // add in a loop all restaurant.getDishes() using restaurant id
        if (restaurant.getCapacity() < 0) {
            throw new ArgumentException("restaurant capacity must not be negative");
        }

//        Restaurant addedRestaurant = restaurantRepository.add(restaurant);
//        Long addedRestaurantId = addedRestaurant.getId();
//        if (restaurant.getDishes() != null) {
//            restaurant.getDishes().forEach(dish -> dishRepository.add(addedRestaurantId, dish));
//        }
//        return get(addedRestaurantId);

        return restaurantRepo.save(restaurant);
    }

    @Override
    @Transactional
    public Restaurant update(Long id, Restaurant restaurant) {
        get(id);
        Restaurant updatedRestaurant = restaurantRepository.update(id, restaurant);
        return get(updatedRestaurant.getId());

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

//        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        get(id);
//        dishRepository.deleteAllByRestaurantId(id);
        dishRepo.deleteByRestaurantId(id);
        restaurantRepo.deleteById(id);// what should happen to a previous SQL when this line of code is faild ?? use @Transactional

//        restaurantRepo.
//        restaurantRepository.delete(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }
}
