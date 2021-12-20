package me.takhir.restaurantapi.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.takhir.restaurantapi.model.Dish;
import me.takhir.restaurantapi.model.Menu;
import me.takhir.restaurantapi.model.PayForDishDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    @HystrixCommand(
            fallbackMethod = "getMenuFallback",
            threadPoolKey = "getMenu",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50"),
            }
    )
    public List<Dish> getMenu() {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Menu menu = restTemplate.exchange("http://kitchen-service/menu", HttpMethod.GET, entity, Menu.class).getBody();
        return menu.getDishes();
    }

    public List<Dish> getMenuFallback() {
        List<Dish> list = new ArrayList<>();
        list.add(new Dish(111L, "", 0.0));
        return list;
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getDishFallback",
            threadPoolKey = "getDish",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50"),
            }
    )
    public Dish getDish(String dishName, Long customerId) throws Exception {
        Dish dish = restTemplate.getForEntity("http://kitchen-service/dish/" + dishName, Dish.class).getBody();
        HttpStatus status = restTemplate.postForEntity("http://payment-service/buy/", new PayForDishDto(customerId, dish.getPrice()), HttpStatus.class).getStatusCode();

        if (status == HttpStatus.OK) return dish;
        else throw new Exception("something went wrong");
    }

    public Dish getDishFallback(String dishName, Long customerId) {
        return new Dish(111L, "", 0.0);
    }
}

