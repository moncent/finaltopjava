package ru.javaops.topjava.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javaops.topjava.MenuTestData;
import ru.javaops.topjava.TestUtil;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.service.MenuService;
import ru.javaops.topjava.util.MenuUtil;
import ru.javaops.topjava.util.exception.NotFoundException;
import ru.javaops.topjava.web.AbstractControllerTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava.MenuTestData.*;
import static ru.javaops.topjava.RestaurantTestData.*;
import static ru.javaops.topjava.UserTestData.ADMIN;
import static ru.javaops.topjava.UserTestData.USER1;

class MenuAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuService menuService;

    public MenuAdminRestControllerTest() {
        super(MenuAdminRestController.REST_URL);
    }

    @Test
    void getToday() throws Exception {
        perform(doGet("/now").basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(MENU_MATCHER.contentJson(List.of()));
    }

    @Test
    void getByDate() throws Exception {
        perform(doGet("/search?date=2019-12-09").basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MENU_MATCHER.contentJson(List.of(MENU1, MENU2)));
    }

    @Test
    void getByDateUnAuth() throws Exception {
        perform(doGet("/search?date=2019-12-09").basicAuth(USER1))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MENU_MATCHER.contentJson(List.of(MENU3, MENU1, MENU2)));
    }

    @Test
    void get() throws Exception {
        perform(doGet(RESTAURANT1_ID +"/" + MENU1_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MENU_MATCHER.contentJson(MENU1));
    }

    @Test
    void getFromUser() throws Exception {
        perform(doGet(RESTAURANT1_ID +"/" + MENU1_ID).basicAuth(USER1))
                .andExpect(status().isForbidden());
    }

    @Test
    void create() throws Exception {
        Menu newMenu = MenuTestData.getNew();
        ResultActions post = perform(doPost().jsonBody(MenuUtil.createTo(newMenu)).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Menu created = TestUtil.readFromJson(post, Menu.class);
        Integer id = created.getId();
        newMenu.setId(id);
        MENU_MATCHER.assertMatch(created, newMenu);
    }


    @Test
    void delete() throws Exception {
        perform(doDelete(MENU3_ID).basicAuth(ADMIN))
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> menuService.get(MENU3_ID, RESTAURANT3_ID));
    }

}