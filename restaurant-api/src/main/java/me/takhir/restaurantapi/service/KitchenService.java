package me.takhir.restaurantapi.service;

import me.takhir.restaurantapi.model.Dish;
import me.takhir.restaurantapi.model.Menu;
import me.takhir.restaurantapi.model.PayForDishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.List;


public interface KitchenService {

    List<Dish> getMenu();

    Dish getDish(String dishName, Long customerId) throws Exception;

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
    public Dish getDish(String dishName, Long customerId) throws Exception {
        Dish dish = restTemplate.getForObject("http://kithcen-service/dish/" + dishName, Dish.class);
        HttpStatus status = restTemplate.postForObject("http://payment-service/buy/", new PayForDishDto(customerId, dish.getPrice()), HttpStatus.class);

        if (status == HttpStatus.OK) return dish;
        else throw new Exception("something went wrong");
    }
}

