package me.takhir.restaurantapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    private Long id;
    private String name;
    private Double price;
}
