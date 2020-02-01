package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    Menu save(Menu menu, int restaurantId, Meal... meals);

    boolean delete(int id);

    List<Menu> getByDate(LocalDate date);

    default boolean saveVote(int id, int restaurantId, int userId) {throw new UnsupportedOperationException();}
}
