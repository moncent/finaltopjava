package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> getAll();

    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);
}
