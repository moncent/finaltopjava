package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.Meal;

import java.util.List;

public interface MealsRepository {

    List<Meal> getAll();

    Meal get(int id);

    Meal save(Meal meal, int restaurantId);

    boolean delete(int id);
}
