package ru.alex.myvote;

import ru.alex.myvote.model.Dish;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.to.RestaurantTo;

import java.util.List;

import static ru.alex.myvote.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class,  "menu", "admin");
    public static TestMatcher<RestaurantTo> RESTAURANT_TO_MATCHER = TestMatcher.usingFieldsComparator(RestaurantTo.class, "dishes");

    public static final int RESTAURANT_ID = START_SEQ + 4;

    public static final Restaurant ADMIN_1_REST_1 = new Restaurant(RESTAURANT_ID, "Rest1",
            List.of(new Dish(null,"Рыба",252), new Dish("Картошка", 53), new Dish("Чай", 533), new Dish("Омлет", 733)), 5);
    public static final Restaurant ADMIN_1_REST_2 = new Restaurant(RESTAURANT_ID + 1, "Rest2",
            List.of(new Dish("Омлет",1210), new Dish("Шашлык", 1036), new Dish("Чай", 533)), 47);
    public static final Restaurant ADMIN_1_REST_3 = new Restaurant(RESTAURANT_ID + 2, "Rest3",
            List.of(new Dish("Картошка",53), new Dish("Шашлык", 1036)), 53);
    public static final Restaurant ADMIN_1_REST_4 = new Restaurant(RESTAURANT_ID + 3, "Rest4",
            List.of(new Dish("Кофе",1530), new Dish("Чай", 254)), 15);
    public static final Restaurant ADMIN_2_REST_1 = new Restaurant(RESTAURANT_ID + 4, "Rest5",
            List.of(new Dish("Омлет",1210), new Dish(), new Dish("Картошка", 120)), 25);
    public static final Restaurant ADMIN_2_REST_2 = new Restaurant(RESTAURANT_ID + 5, "Rest6",
            List.of(new Dish("Картошка",53), new Dish(), new Dish("Икра", 2530)), 71);

    public static final List<Restaurant> RESTAURANTS = List.of(ADMIN_1_REST_1, ADMIN_1_REST_2, ADMIN_1_REST_3, ADMIN_1_REST_4, ADMIN_2_REST_1, ADMIN_2_REST_2);

    public static Restaurant getNew() {
        return new Restaurant(null,"newRest", List.of(new Dish("Картошка",53), new Dish("Икра", 2530)), 0);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Rest1", List.of(new Dish("Картошка",53), new Dish("Икра", 2530)), 200);
    }
}
