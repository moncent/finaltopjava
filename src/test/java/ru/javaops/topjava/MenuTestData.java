package ru.javaops.topjava;

import ru.javaops.topjava.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static java.util.List.of;
import static ru.javaops.topjava.MealTestData.*;
import static ru.javaops.topjava.RestaurantTestData.*;
import static ru.javaops.topjava.UserTestData.ADMIN_ID;

public class MenuTestData {
    public static final int MENU1_ID = ADMIN_ID + 12;
    public static final int MENU2_ID = ADMIN_ID + 13;
    public static final int MENU3_ID = ADMIN_ID + 14;

    public static final Menu MENU1 = new Menu(MENU1_ID, LocalDate.of(2019,12,9), 0, of(MEAL2, MEAL3, MEAL4), RESTAURANT1);
    public static final Menu MENU2 = new Menu(MENU2_ID, LocalDate.of(2019, 12,9), 0, of(MEAL1), RESTAURANT2);
    public static final Menu MENU3 = new Menu(MENU3_ID, LocalDate.of(2019, 12,10), 1, of(MEAL5, MEAL6), RESTAURANT3);

    public static List<Menu> ALL_MENU_ORDER_BY_DATE_DESC = List.of(MENU3, MENU1, MENU2);

    public static Menu getNew() {
        return new Menu(null, LocalDate.now(),  of(MEAL6), RESTAURANT2);
    }

    public static Menu getUpdated() {
        return new Menu(MENU2_ID, MENU2.getLunchDate(), of(MEAL2, MEAL4), RESTAURANT2);
    }

    public static TestMatchers<Menu> MENU_MATCHER = TestMatchers.useFieldsComparator(Menu.class, "users", "menuItems", "restaurant");

}
