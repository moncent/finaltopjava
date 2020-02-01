package ru.javaops.topjava.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.javaops.topjava.model.Restaurant;
import ru.javaops.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.RestaurantTestData.*;
import static ru.javaops.topjava.UserTestData.USER1;

class RestaurantProfileRestControllerTest extends AbstractControllerTest {


    public RestaurantProfileRestControllerTest() {
        super(RestaurantProfileRestController.REST_URL);
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(USER1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANTS_MATCHER.contentJson(RESTAURANTS_ORDERED_ALPHABET));
    }

    @Test
    void get() throws Exception {
        perform(doGet(RESTAURANT1_ID).basicAuth(USER1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANTS_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void updateNotAllowed() throws Exception {
        Restaurant updated = new Restaurant(getUpdated());
        perform(doPost().jsonBody(updated).basicAuth(USER1))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    void createNotAllowed() throws Exception {
        Restaurant created = new Restaurant(getNew());
        perform(doPost().jsonBody(created).basicAuth(USER1))
                .andExpect(status().isMethodNotAllowed());
    }
}