package ru.alex.myvote.web.json;

import org.junit.jupiter.api.Test;
import ru.alex.myvote.model.Restaurant;

import java.util.List;

import static ru.alex.myvote.RestaurantTestData.*;

class JsonUtilTest {

    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ADMIN_1_REST_1);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurant, ADMIN_1_REST_1);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANTS);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANTS);
    }
}