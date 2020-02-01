package ru.javaops.topjava.util;

import ru.javaops.topjava.model.Menu;
import ru.javaops.topjava.to.MenuTo;

public class MenuUtil {

    private MenuUtil() {
    }

    public static MenuTo createTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getLunchDate(), menu.getRestaurant(), menu.getMenuItems());
    }

    public static Menu createFromTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getLunchDate(), menuTo.getMenuItems(), menuTo.getRestaurant());
    }

}