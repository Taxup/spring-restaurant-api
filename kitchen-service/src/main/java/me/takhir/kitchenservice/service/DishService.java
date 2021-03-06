package me.takhir.kitchenservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.takhir.kitchenservice.model.Dish;
import me.takhir.kitchenservice.model.Menu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface DishService {

    Menu getMenu();

    Dish getDish(String dishName);

}

@Service
class DishServiceIpml implements DishService {

    private static List<Dish> dishes = new ArrayList<Dish>() {
        {
            add(new Dish( 1L, "Bread" , 150.0));
            add(new Dish( 2L, "Tort" , 6100.0));
        }
    };

    @Override
    public Menu getMenu() {
        Menu menu = new Menu();
        menu.setDishes(dishes);
        return menu;
    }

    public Dish getDish(String dishName) {
        Dish requiredDish = null;
        for (Dish dish: dishes) {
            if (dishName.equals(dish.getName())) {
                requiredDish = dish;
                break;
            }
        }
        return requiredDish;
    }

}
