package ru.javaops.topjava;

import ru.javaops.topjava.model.Restaurant;

import java.util.List;

import static ru.javaops.topjava.UserTestData.ADMIN_ID;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = ADMIN_ID + 3;
    public static final int RESTAURANT2_ID = ADMIN_ID + 4;
    public static final int RESTAURANT3_ID = ADMIN_ID + 5;


    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Seraph(RUS)", 222222, "Russia", "Moscow", "Ilyinka street");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Seraph(US)", 111111,"USA", "NY",	"Wall street");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT3_ID, "Seraph(IT)", 333333,"Italy",  "Rome", "Nizza street");

    public static List<Restaurant> RESTAURANTS_ORDERED_ALPHABET = List.of(RESTAURANT3, RESTAURANT1, RESTAURANT2);

    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant", 444444, "Country", "City", "Address");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, RESTAURANT1.getName(), RESTAURANT1.getZip(), RESTAURANT1.getCountry(), RESTAURANT1.getCity(), "Updated street");
    }

    public static TestMatchers<Restaurant> RESTAURANTS_MATCHER = TestMatchers.useEquals(Restaurant.class);

}
