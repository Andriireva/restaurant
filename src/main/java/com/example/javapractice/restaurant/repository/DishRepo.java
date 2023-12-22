package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {

    // Spring data related
    void deleteByRestaurantId(Long restId);
    // it creates an actual implementation on the background:
    // delete from Dish.name where restaurant_Id = restId
}
