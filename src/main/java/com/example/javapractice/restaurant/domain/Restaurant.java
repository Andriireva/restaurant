package com.example.javapractice.restaurant.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.function.Predicate;

@Entity
@Table(name = "restaurants") // refers to a table name in data base
public class Restaurant extends AbstractAuditable {

    @Id
    // Code bellow refers to an actual postgres sequence of restaurants id related sequence
    @GeneratedValue(generator = "restaurants_generator")
    @SequenceGenerator(name = "restaurants_generator", sequenceName = "restaurants_id_seq", allocationSize = 1)
//    @GeneratedValue
    private Long id;
    private String name;

    private Instant startWorkDate; //
    private String address;
    private Integer capacity;
    private Boolean allowToGo;
    private Double areaSquare;

    @Transient // excluding this field from sql queries
    private List<Dish> dishes;

    public Restaurant(String name, Instant startWorkDate, String address,
                      Integer capacity, Boolean allowToGo, Double areaSquare, List<Dish> dishes) {
        this.name = name;
        this.startWorkDate = startWorkDate;
        this.address = address;
        this.capacity = capacity;
        this.allowToGo = allowToGo;
        this.areaSquare = areaSquare;
        this.dishes = dishes;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Instant startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getAllowToGo() {
        return allowToGo;
    }

    public void setAllowToGo(Boolean allowToGo) {
        this.allowToGo = allowToGo;
    }

    public Double getAreaSquare() {
        return areaSquare;
    }

    public void setAreaSquare(Double areaSquare) {
        this.areaSquare = areaSquare;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", startWorkDate=" + startWorkDate +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", allowToGo=" + allowToGo +
                ", areaSquare=" + areaSquare +
                ", dishes=" + dishes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
