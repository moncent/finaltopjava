package ru.javaops.topjava.web.menu;

import org.junit.jupiter.api.Test;
import ru.javaops.topjava.web.AbstractControllerTest;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.MenuTestData.MENU_MATCHER;
import static ru.javaops.topjava.UserTestData.USER1;

class MenuProfileRestControllerTest extends AbstractControllerTest {

    public MenuProfileRestControllerTest() {
        super(MenuProfileRestController.REST_URL);
    }

    @Test
    void getToday() throws Exception {
        perform(doGet("/now").basicAuth(USER1))
                .andExpect(status().isOk())
                .andExpect(MENU_MATCHER.contentJson(List.of()));
    }

    @Test
    void getTodayUnAuth() throws Exception {
        perform(doGet("/now"))
                .andExpect(status().isUnauthorized());
    }

}