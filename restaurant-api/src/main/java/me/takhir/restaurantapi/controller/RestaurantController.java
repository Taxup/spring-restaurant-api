package me.takhir.restaurantapi.controller;

import me.takhir.restaurantapi.model.Dish;
import me.takhir.restaurantapi.model.Menu;
import me.takhir.restaurantapi.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class RestaurantController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<List<Dish>> getMenu() {
        return ResponseEntity.ok(kitchenService.getMenu());
    }

    @GetMapping("/order/{dishName}")
    public ResponseEntity<Dish> order(@PathVariable String dishName, @RequestParam Long customerId) throws Exception {
        return ResponseEntity.ok(kitchenService.getDish(dishName, customerId));
    }


}
