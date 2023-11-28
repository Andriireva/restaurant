package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Dish;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
        try {
            return jdbcTemplate.queryForObject("select * from dishes where id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Dish add(Long restaurantId, Dish dish) {
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {

            // the main point is to prepare SQL
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.dishes(\n" +
                            "\tname, price, cook_time_minutes, notes, type, restaurant_id)\n" +
                            "\tVALUES (?, ?, ?, ?, ?, ?)",
                    new String[]{"id"});
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setDouble(2, dish.getPrice());
            preparedStatement.setInt(3, dish.getCookTimeMinutes());
            preparedStatement.setString(4, dish.getNotes());
            preparedStatement.setString(5, dish.getType().toString());
            preparedStatement.setLong(6, restaurantId);
            return preparedStatement;

        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        Number keyGenerated = generatedKeyHolder.getKey();
        Long key = keyGenerated.longValue();

        return get(key);
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

    @Override
    public List<Dish> getAllByRestaurantId(Long restaurantId) {
        List<Dish> dishes = jdbcTemplate.query("select * from dishes where restaurant_id = ?", rowMapper, restaurantId);
        return dishes;
    }

    @Override
    public void deleteAllByRestaurantId(Long restaurantId) {
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.dishes " +
                    "where restaurant_id = ?"
            );
            preparedStatement.setLong(1, restaurantId);
            return preparedStatement;

        };
        jdbcTemplate.update(preparedStatementCreator);
    }
}
