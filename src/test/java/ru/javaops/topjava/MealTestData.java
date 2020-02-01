package ru.javaops.topjava;

import ru.javaops.topjava.model.Meal;

import java.util.List;

import static ru.javaops.topjava.RestaurantTestData.*;
import static ru.javaops.topjava.UserTestData.ADMIN_ID;

public class MealTestData {
    public static final int MEAL1_ID = ADMIN_ID + 6;
    public static final int MEAL2_ID = ADMIN_ID + 7;
    public static final int MEAL3_ID = ADMIN_ID + 8;
    public static final int MEAL4_ID = ADMIN_ID + 9;
    public static final int MEAL5_ID = ADMIN_ID + 10;
    public static final int MEAL6_ID = ADMIN_ID + 11;


    public static final Meal MEAL1 = new Meal(MEAL1_ID, "Borsch", 2000, RESTAURANT1);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, "Eggs", 200, RESTAURANT1);
    public static final Meal MEAL3 = new Meal(MEAL3_ID, "Black Tea", 100, RESTAURANT1);
    public static final Meal MEAL4 = new Meal(MEAL4_ID, "Roast Beef", 5000, RESTAURANT2);
    public static final Meal MEAL5 = new Meal(MEAL5_ID, "Roast Beef", 7000, RESTAURANT3);
    public static final Meal MEAL6 = new Meal(MEAL6_ID, "Pizza", 10000, RESTAURANT3);


    public static List<Meal> ALL_MEALS_ORDERED_BY_NAME_PRICE = List.of(MEAL3, MEAL1, MEAL2, MEAL6, MEAL4, MEAL5);

    public static Meal getNew() {
        return new Meal(null, "NewMeal", 1000, RESTAURANT1);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, "Updated Roast Beef", 8000, RESTAURANT1);
    }

    public static TestMatchers<Meal> MEAL_MATCHER =TestMatchers.useFieldsComparator(Meal.class, "menus");

}
