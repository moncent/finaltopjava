package ru.javaops.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.repository.MealsRepository;
import ru.javaops.topjava.repository.RestaurantRepository;

import java.util.List;

@Repository
public class MealsRepositoryImpl implements MealsRepository {

    private final CrudMealsRepository mealsRepository;
    private final RestaurantRepository restaurantRepository;


    public MealsRepositoryImpl(CrudMealsRepository mealsRepository,
                               RestaurantRepository restaurantRepository) {
        this.mealsRepository = mealsRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Meal> getAll() {
        return mealsRepository.findAll(Sort.by(Sort.Order.asc("name"), Sort.Order.asc("price")));
    }

    @Override
    public Meal get(int id) {
        return mealsRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        meal.setRestaurant(restaurantRepository.get(restaurantId));
        return mealsRepository.save(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
       return mealsRepository.delete(id) != 0;
    }
}
