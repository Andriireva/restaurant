package com.example.javapractice.restaurant.repository;

import com.example.javapractice.restaurant.domain.Restaurant;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

// IoC - inversion of control - it means that we as developers give responsibility to create (or get ) an instance of classes
//       instead of developer Spring will call "new" operation and will call a constructors and will "some how" initialize dependency classes
//


@Component // It spring annotation that says, when spring application starts,
            // please initialize instance of this class
public class RestaurantRepository implements IRestaurantRepository {

    // We will use ORM ( object relation mapping )
    private final JdbcTemplate jdbcTemplate;


    // RowMapper is interface that map ResultSet (represents a single row from sql result java natively) to a
    // class that is in <>
    private final RowMapper<Restaurant> rowMapper;

    public RestaurantRepository(JdbcTemplate jdbcTemplate
//                                RowMapper<Restaurant> rowMapper
    ) {
        System.out.println("RestaurantRepository constructor is called");
        System.out.println(((HikariDataSource) jdbcTemplate.getDataSource()).getJdbcUrl());
        this.jdbcTemplate = jdbcTemplate;
        // BeanPropertyRowMapper it a classs that it tries to get columnNames
        // and call appropriate setter over Restaurant.class
        // Steps:
        // 1. it creates instance by calling default contructor
        // 2. in a loop of columns execute start_work_date -> calls setStartWorkDate(
        this.rowMapper = new BeanPropertyRowMapper<>(Restaurant.class);

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

        Restaurant restaurant = jdbcTemplate.queryForObject("select * from restaurants where id = " + id, rowMapper);
//        jdbcTemplate.queryForObject("select * from restaurants where id = ?", rowMapper, id);

        return restaurant;
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Long id, Restaurant updatedRestaurant) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
