package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Restaurant;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

// IoC - inversion of control - it means that we as developers give responsibility to create (or get ) an instance of classes
//       instead of developer Spring will call "new" operation and will call a constructors and will "some how" initialize dependency classes
//


@Component // Its spring annotation that says, when spring application starts,
// please initialize instance of this class
public class RestaurantRepository implements IRestaurantRepository {

    // We will use ORM ( object relation mapping )
    private final JdbcTemplate jdbcTemplate;


    // RowMapper is interface that map ResultSet (represents a single row from sql result java natively) to a
    // class that is in <>
    private final RowMapper<Restaurant> rowMapper;

    public RestaurantRepository(JdbcTemplate jdbcTemplate,
//                  It is legacy way  @Qualifier("restaurantRowMapper") RowMapper<Restaurant> rowMapper
                                RowMapper<Restaurant> restaurantRowMapper
    ) {
        System.out.println("RestaurantRepository constructor is called");
        System.out.println(((HikariDataSource) jdbcTemplate.getDataSource()).getJdbcUrl());
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = restaurantRowMapper;
        // BeanPropertyRowMapper it a classs that it tries to get columnNames
        // and call appropriate setter over Restaurant.class
        // Steps:
        // 1. it creates instance by calling default contructor
        // 2. in a loop of columns execute start_work_date -> calls setStartWorkDate(
//        this.rowMapper = new BeanPropertyRowMapper<>(Restaurant.class);

        // ResultSet represents a single row from sql result java natively
//        this.rowMapper = (ResultSet rs, int rowNum) -> {
//            Restaurant restaurant = new Restaurant();
//            String name = rs.getString("name");
//            restaurant.setName(name);
//            return restaurant;
//        };

    }

    @Override
    public Restaurant get(Long id) {
        // we want to execute an actual SQL script select * from restaurant where id = id
        // than map somehow the result to an instance of Restaurant

        Restaurant restaurant = null;
        try {
            restaurant = jdbcTemplate.queryForObject("select * from restaurants where id = " + id, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
//        jdbcTemplate.queryForObject("select * from restaurants where id = ?", rowMapper, id);

        return restaurant;
    }

    @Override
    public Restaurant add(Restaurant restaurantToBeAdded) {
        // INSERT
        // INTO public.restaurants(name, start_work_date, address, capacity)
        //	VALUES ('Rest Fishbar', '2017-11-06 07:52:01.053218', 'Greenforest st 55', 15);
        // How to get id of newly added entry ???
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {

            // the main point is to prepare SQL
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.restaurants(\n" +
                            "\tname, start_work_date, address, capacity, allow_to_go, area_square)\n" +
                            "\tVALUES (?, ?, ?, ?, ?, ?)", // FIRST index is 1 ( NOT ZERO ) !!!!
                    new String[]{"id"});
            preparedStatement.setString(1, restaurantToBeAdded.getName());
            preparedStatement.setTimestamp(2, Timestamp.from(restaurantToBeAdded.getStartWorkDate()));
            preparedStatement.setString(3, restaurantToBeAdded.getAddress());
            preparedStatement.setInt(4, restaurantToBeAdded.getCapacity());
            preparedStatement.setBoolean(5, restaurantToBeAdded.getAllowToGo());
            preparedStatement.setDouble(6, restaurantToBeAdded.getAreaSquare());
            return preparedStatement;

        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        Number keyGenerated = generatedKeyHolder.getKey(); // simplified version
        Map<String, Object> keys = generatedKeyHolder.getKeys(); // Map<
        Long key = keyGenerated.longValue(); // it returns generated id of a newly added restaurant

        return get(key);
//        return null;
    }

    @Override
    public Restaurant update(Long id, Restaurant updatedRestaurant) {
//        UPDATE public.restaurants
//        SET id=?, name=?, start_work_date=?, address=?, capacity=?, allow_to_go=?, area_square=?
//        WHERE <condition>

        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {

            // the main point is to prepare SQL
            PreparedStatement preparedStatement = connection.prepareStatement("        UPDATE public.restaurants\n" +
                            "        SET name=?, start_work_date=?, address=?, capacity=?, allow_to_go=?, area_square=?\n" +
                            "        WHERE id = ?" // FIRST index is 1 ( NOT ZERO ) !!!!
                    );
            preparedStatement.setString(1, updatedRestaurant.getName());
            preparedStatement.setTimestamp(2, Timestamp.from(updatedRestaurant.getStartWorkDate()));
            preparedStatement.setString(3, updatedRestaurant.getAddress());
            preparedStatement.setInt(4, updatedRestaurant.getCapacity());
            preparedStatement.setBoolean(5, updatedRestaurant.getAllowToGo());
            preparedStatement.setDouble(6, updatedRestaurant.getAreaSquare());
            preparedStatement.setLong(7, id);
            return preparedStatement;

        };
        jdbcTemplate.update(preparedStatementCreator);
        return get(id);
    }

    @Override
    public void delete(Long id) {
        // DELETE FROM public.restaurants
        //	WHERE <condition>
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {

            // the main point is to prepare SQL
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.restaurants " +
                    "where id = ?" // FIRST index is 1 ( NOT ZERO ) !!!!
            );
            preparedStatement.setLong(1, id);
            return preparedStatement;

        };
        jdbcTemplate.update(preparedStatementCreator);

    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query("select * from restaurants", rowMapper);
    }
}
