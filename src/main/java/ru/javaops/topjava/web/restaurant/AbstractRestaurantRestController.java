package ru.javaops.topjava.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.service.RestaurantService;

import java.util.List;

public abstract class AbstractRestaurantRestController {

    @Autowired
    protected RestaurantService restaurantService;

    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    public Restaurant get(@PathVariable int id) {
        return restaurantService.get(id);
    }

}
