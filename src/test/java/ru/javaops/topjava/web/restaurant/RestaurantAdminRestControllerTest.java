package ru.javaops.topjava.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaops.topjava.TestUtil;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.service.RestaurantService;
import ru.javaops.topjava.util.exception.NotFoundException;
import ru.javaops.topjava.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.RestaurantTestData.*;
import static ru.javaops.topjava.UserTestData.ADMIN;

class RestaurantAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService restaurantService;

    public RestaurantAdminRestControllerTest() {
        super(RestaurantAdminRestController.REST_URL);
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANTS_MATCHER.contentJson(RESTAURANTS_ORDERED_ALPHABET));
    }

    @Test
    void get() throws Exception {
        perform(doGet(RESTAURANT1_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANTS_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getNew();
        ResultActions post = perform(doPost().jsonBody(newRestaurant).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Restaurant created = TestUtil.readFromJson(post, Restaurant.class);
        Integer id = created.getId();
        newRestaurant.setId(id);
        RESTAURANTS_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANTS_MATCHER.assertMatch(restaurantService.get(id), newRestaurant);

    }

    @Test
    void createInvalid() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "", 88888888, "", "", "");
        perform(doPost().jsonBody(newRestaurant).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(getUpdated());
        perform(doPut(RESTAURANT1_ID).jsonBody(updated).basicAuth(ADMIN))
                .andExpect(status().isNoContent());

        RESTAURANTS_MATCHER.assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    void updateInvalid() throws Exception {
        Restaurant updated = new Restaurant(getUpdated());
        updated.setAddress("");
        perform(doPut(RESTAURANT1_ID).jsonBody(updated).basicAuth(ADMIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(RESTAURANT3_ID).basicAuth(ADMIN))
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT3_ID));
    }
}