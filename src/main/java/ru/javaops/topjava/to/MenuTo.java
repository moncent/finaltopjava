package ru.javaops.topjava.to;


import org.springframework.format.annotation.DateTimeFormat;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MenuTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 4410110003838596510L;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate lunchDate;

    private List<Meal> menuItems;

    @NotNull
    private Restaurant restaurant;

    public MenuTo(Integer id, LocalDate lunchDate, Restaurant restaurant,  List<Meal> menuItems) {
        super(id);
        this.lunchDate = lunchDate;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
    }

    public MenuTo() {}

    public LocalDate getLunchDate() {
        return lunchDate;
    }

    public List<Meal> getMenuItems() {
        return menuItems;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setLunchDate(LocalDate lunchDate) {
        this.lunchDate = lunchDate;
    }

    public void setMenuItems(List<Meal> menuItems) {
        this.menuItems = menuItems;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "lunchDate=" + lunchDate +
                ", menuItems=" + menuItems +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
