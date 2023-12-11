package com.example.javapractice.restaurant.config;

import com.example.javapractice.restaurant.domain.Restaurant;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component("restaurantRowMapperNotCompletlyImplemented")
// By dafault bean name is Class but start from lower char
// default bean name restaurantRowMapper
//@Primary
public class RestaurantRowMapper implements RowMapper<Restaurant> {
    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        // ResultSet represtans a single row result
        Restaurant restaurant = new Restaurant();
        String name = rs.getString("name");
        Long id = rs.getLong("id");
        double price = rs.getDouble("price");

        restaurant.setName(name);
        return restaurant;
//        return null;
    }
}
