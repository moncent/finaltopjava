package ru.javaops.topjava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javaops.topjava.MenuTestData;
import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javaops.topjava.MenuTestData.*;
import static ru.javaops.topjava.RestaurantTestData.*;

@SpringJUnitConfig(locations = {
        "/spring/spring-app.xml",
        "/spring/spring-db.xml"
})
@Sql(scripts = "/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void create() {
        Menu newMenu = MenuTestData.getNew();
        Menu created = menuService.create(new Menu(newMenu), RESTAURANT2_ID);
        Integer id = created.getId();
        newMenu.setId(id);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(id, RESTAURANT2_ID), newMenu);
    }

    @Test
    void update() {
        Menu updated = MenuTestData.getUpdated();
        menuService.update(new Menu(updated), RESTAURANT2_ID);
        updated.setRestaurant(RESTAURANT2);
        MENU_MATCHER.assertMatch(menuService.get(MENU2_ID, RESTAURANT2_ID), updated);
    }

    @Test
    void delete() {
        menuService.delete(MENU2_ID);
        assertThrows(NotFoundException.class, () -> menuService.delete(MENU2_ID));
    }

    @Test
    void get() {
        Menu menu = menuService.get(MENU1_ID, MENU1.getRestaurant().getId());
        MENU_MATCHER.assertMatch(menu, MENU1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> menuService.get(MENU1_ID, RESTAURANT3_ID));
    }

    @Test
    void getAll() {
        List<Menu> all = menuService.getAll();
        MENU_MATCHER.assertMatch(all, ALL_MENU_ORDER_BY_DATE_DESC);
    }

    @Test
    void getByDate() {
        List<Menu> menus = menuService.getByDate(LocalDate.of(2019, 12,10));
        MENU_MATCHER.assertMatch(menus, List.of(MENU3));
    }

    @Test
    void getByCurrentDay() {
        List<Menu> menus = menuService.getByCurrentDay();
        MENU_MATCHER.assertMatch(menus, List.of());
    }
}