package com.example.javapractice.restaurant.domaindesign.dish;


// Domain class explain a single entry in database
public class Dish {
    private String name;
    private Double price;
    private Integer cookTimeMinutes;
    private String notes;
    private DishType type;

    public Dish(String name, Double price, Integer cookTimeMinutes, String notes, DishType type) {
        this.name = name;
        this.price = price;
        this.cookTimeMinutes = cookTimeMinutes;
        this.notes = notes;
        this.type = type;
    }

    public Dish() {
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
}
