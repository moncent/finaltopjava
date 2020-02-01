package ru.javaops.topjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javaops.topjava.MealTestData.*;

@SpringJUnitConfig(locations = {
        "/spring/spring-app.xml",
        "/spring/spring-db.xml"
})
@Sql(scripts = "/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    void getAll() {
        List<Meal> all = mealService.getAll();
        MEAL_MATCHER.assertMatch(all, ALL_MEALS_ORDERED_BY_NAME_PRICE);
    }

    @Test
    void get() {
        Meal meal = mealService.get(MEAL1_ID);
        MEAL_MATCHER.assertMatch(meal, MEAL1);
    }

    @Test
    void create() {
        Meal newMeal= getNew();
        Meal created = mealService.create(new Meal(newMeal), newMeal.getRestaurant().getId());
        Integer id = created.getId();
        newMeal.setId(id);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(id), newMeal);
    }

    @Test
    void update() {
        Meal updated = getUpdated();
        mealService.update(new Meal(updated), updated.getRestaurant().getId());
        MEAL_MATCHER.assertMatch(mealService.get(updated.getId()), updated);
    }

    @Test
    void delete() {
        mealService.delete(MEAL1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> mealService.delete(MEAL1_ID));
    }
}