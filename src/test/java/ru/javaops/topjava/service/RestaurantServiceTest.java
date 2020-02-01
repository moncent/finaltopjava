package ru.javaops.topjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javaops.topjava.RestaurantTestData.*;

@SpringJUnitConfig(locations = {
        "/spring/spring-app.xml",
        "/spring/spring-db.xml"
})
@Sql(scripts = "/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void create() {
        Restaurant newRestaurant = getNew();
        Restaurant created = restaurantService.create(new Restaurant(newRestaurant));
        Integer createdId = created.getId();
        newRestaurant.setId(createdId);
        RESTAURANTS_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANTS_MATCHER.assertMatch(restaurantService.get(createdId), newRestaurant);
    }

    @Test
    void delete() {
        restaurantService.delete(RESTAURANT1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> restaurantService.delete(RESTAURANT1_ID));
    }

    @Test
    void get() {
        Restaurant restaurant = restaurantService.get(RESTAURANT1_ID);
        RESTAURANTS_MATCHER.assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    void getAll() {
        List<Restaurant> all = restaurantService.getAll();
        RESTAURANTS_MATCHER.assertMatch(all, RESTAURANTS_ORDERED_ALPHABET);
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        restaurantService.update(new Restaurant(updated));
        RESTAURANTS_MATCHER.assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }
}