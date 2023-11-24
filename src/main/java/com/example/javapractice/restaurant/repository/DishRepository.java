package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Dish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

// Repository is a class that help to operate (add, update, delete, modify) over Entity on a database (storage )
// method here must be "stupid"
// later it will have method to interact with SQL database
@Component
public class DishRepository implements IDishRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Dish> rowMapper;

    public DishRepository(JdbcTemplate jdbcTemplate,
                          RowMapper<Dish> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Dish get(Long id) {
        return null;
    }

    @Override
    public Dish add(Long restaurantId, Dish dish) {
        return null;
    }

    @Override
    public Dish update(Long id, Dish dishToBeAdded) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Dish> getAll() {
        return null;
    }
}
