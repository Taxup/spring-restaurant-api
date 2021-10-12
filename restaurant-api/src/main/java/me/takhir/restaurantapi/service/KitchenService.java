package me.takhir.restaurantapi.service;

import me.takhir.restaurantapi.model.Dish;
import me.takhir.restaurantapi.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public interface KitchenService {

    List<Dish> getMenu();

    Dish getDish(String dishName, Long customerId);

}

@Service
class KitchenServiceImpl implements KitchenService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Dish> getMenu() {
        Menu menu = restTemplate.getForObject("http://kitchen-service/menu", Menu.class);
        return menu.getDishes();
    }

    @Override
    public Dish getDish(String dishName, Long customerId) {
        Dish dish = restTemplate.getForObject("http://kithcen-service/dish/buy/" + dishName + "?userId=" + customerId, Dish.class);
        return dish;
    }
}
