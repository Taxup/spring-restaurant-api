package me.takhir.restaurantapi.model;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private List<Dish> dishes;
}
