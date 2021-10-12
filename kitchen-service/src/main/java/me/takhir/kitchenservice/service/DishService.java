package me.takhir.kitchenservice.service;

import me.takhir.kitchenservice.model.Dish;
import me.takhir.kitchenservice.model.Menu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface DishService {

    Menu getMenu();

}

@Service
class DishServiceIpml implements DishService {

    @Override
    public Menu getMenu() {
        Menu menu = new Menu();
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish( 1L, "Bread" , 150.0));
        dishes.add(new Dish( 2L, "Tort" , 6100.0));
        menu.setDishes(dishes);
        return menu;
    }

}
