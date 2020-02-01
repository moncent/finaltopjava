package ru.javaops.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaops.topjava.MealTestData;
import ru.javaops.topjava.TestUtil;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.service.MealService;
import ru.javaops.topjava.util.exception.NotFoundException;
import ru.javaops.topjava.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.MealTestData.*;
import static ru.javaops.topjava.RestaurantTestData.*;
import static ru.javaops.topjava.UserTestData.ADMIN;
import static ru.javaops.topjava.UserTestData.USER1;

class MealAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MealService mealService;

    public MealAdminRestControllerTest() {
        super(MealAdminRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(MEAL1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MEAL1));
    }

   @Test
    void getFromUser() throws Exception {
        perform(doGet(RESTAURANT1_ID + "/" + MEAL1_ID).basicAuth(USER1))
                .andExpect(status().isForbidden());
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(MEAL1_ID).basicAuth(ADMIN))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL1_ID));
    }

    @Test
    void deleteFromUser() throws Exception {
        perform(doDelete(MEAL1_ID).basicAuth(USER1))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(ALL_MEALS_ORDERED_BY_NAME_PRICE));
    }

    @Test
    void getAllUnAuth() throws Exception {
        perform(doGet()).andExpect(status().isUnauthorized());
    }

    @Test
    void update() throws Exception {
        Meal updated  = MealTestData.getUpdated();
        perform(doPut(MEAL1_ID).jsonBody(updated).basicAuth(ADMIN))
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(mealService.get(MEAL1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Meal newMeal  = MealTestData.getNew();
        ResultActions actions = perform(doPost().jsonBody(newMeal).basicAuth(ADMIN))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Meal created = TestUtil.readFromJson(actions, Meal.class);
        Integer id = created.getId();
        newMeal.setId(id);

        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(id), newMeal);
    }
}