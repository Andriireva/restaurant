package com.example.javapractice.restaurant.config;

import com.example.javapractice.restaurant.domain.Dish;
import com.example.javapractice.restaurant.domain.Restaurant;
import com.example.javapractice.restaurant.repository.IRestaurantRepository;
import com.example.javapractice.restaurant.repository.RestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

// This class is class that store meta information how spring should init "bean"
@Configuration
public class ApplicationConfig {

    // ALl methods that marked as a Bean it say - create "variable" of RowMapper<Restaurant> by calling this method
    // 1. RowMapper<Restaurant> restaurantRowMapper = restaurantRowMapper()
    // 2. Store this variable restaurantRowMapper in a specific "Map<BEAN_NAME, VALUE> " ( spring container )
    // Factory method

    // Without setting a name to a bean annotation name comes from method name
//    @Bean("restaurantRowMapperMyCustomName")
    @Bean
    public RowMapper<Restaurant> restaurantRowMapper() {
        System.out.println("ApplicationConfig.restaurantRowMapper is called");
        return new BeanPropertyRowMapper<>(Restaurant.class);
    }

    @Bean
    public RowMapper<Dish> dishRowMapper() {
        System.out.println("ApplicationConfig.dishRowMapper is called");
        return new BeanPropertyRowMapper<>(Dish.class);
    }


//    @Bean
//    public IRestaurantRepository restaurantRepository(
//            JdbcTemplate template,
//            RowMapper<Restaurant> rowMapper) {
//        return new RestaurantRepository(template, rowMapper);
//    }

}
