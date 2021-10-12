package me.takhir.kitchenservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Menu {
    private List<Dish> dishes;
}
