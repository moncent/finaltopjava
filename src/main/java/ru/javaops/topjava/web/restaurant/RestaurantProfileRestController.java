package ru.javaops.topjava.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL)
public class RestaurantProfileRestController extends AbstractRestaurantRestController {
    static final String REST_URL = "/rest/profile/restaurant";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
