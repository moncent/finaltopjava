package ru.javaops.topjava.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.repository.MealsRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealsRepository mealsRepository;

    public MealService(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    public List<Meal> getAll() {
        return mealsRepository.getAll();
    }

    public Meal get(int id) {
        return checkNotFoundWithId(mealsRepository.get(id), id);
    }

    public Meal create(Meal meal, @NotNull Integer restaurantId) {
        Assert.notNull(meal, "meal mustn't be null!");
        return mealsRepository.save(meal, restaurantId);
    }

    public void update(Meal meal, @NotNull Integer restaurantId) {
        Assert.notNull(meal, "meal mustn't be null!");
        checkNotFoundWithId(mealsRepository.save(meal, restaurantId), meal.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(mealsRepository.delete(id), id);
    }

}