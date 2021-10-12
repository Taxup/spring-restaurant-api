package me.takhir.kitchenservice.controller;

import me.takhir.kitchenservice.model.Dish;
import me.takhir.kitchenservice.model.Menu;
import me.takhir.kitchenservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DishController {

    @Autowired
    DishService dishService;

    @GetMapping("/menu")
    public ResponseEntity<Menu> getMenu() {
        return ResponseEntity.ok(dishService.getMenu());
    }

    @GetMapping("/dish/{dishName}")
    public ResponseEntity<Dish> getDish(@PathVariable String dishName) {
        return ResponseEntity.ok(dishService.getDish(dishName));
    }
}
