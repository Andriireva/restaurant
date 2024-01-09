package com.example.javapractice.restaurant.domain;

// Domain class explain a single entry in database

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractAuditable {

    @Id
    // Code bellow refers to an actual postgres sequence of restaurants id related sequence
    @GeneratedValue(generator = "restaurants_generator")
    @SequenceGenerator(name = "restaurants_generator", sequenceName = "restaurants_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Double price;
    private Integer cookTimeMinutes; // DB: cook_time_minutes
    private String notes;

    @Enumerated(EnumType.STRING)
    private DishType type;
    private Long restaurantId;

    public Dish(Long id, String name, Double price, Integer cookTimeMinutes, String notes, DishType type, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cookTimeMinutes = cookTimeMinutes;
        this.notes = notes;
        this.type = type;
        this.restaurantId = restaurantId;
    }

    public Dish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(Integer cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", cookTimeMinutes=" + cookTimeMinutes +
                ", notes='" + notes + '\'' +
                ", type=" + type +
                '}';
    }
}
