package me.takhir.restaurantapi.model;

import lombok.Data;

@Data
public class Dish {
    private Long id;
    private String name;
    private Double price;
}
