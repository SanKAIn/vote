package ru.alex.myvote.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alex.myvote.RestaurantTestData;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.repository.RestaurantRepository;
import ru.alex.myvote.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.alex.myvote.RestaurantTestData.*;
import static ru.alex.myvote.UserTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private RestaurantRepository repository;

    @Test
    void delete() {
        service.delete(RESTAURANT_ID, ADMIN1_ID);
        Assertions.assertNull(repository.get(RESTAURANT_ID, ADMIN1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class,
                () -> service.delete(1, USER1_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class,
                () -> service.delete(RESTAURANT_ID, ADMIN2_ID));
    }

    @Test
    void create() {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        Restaurant created = service.create(newRestaurant, USER1_ID);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId, USER1_ID), newRestaurant);
    }

    @Test
    void get() {
        Restaurant actual = service.get(RESTAURANT_ID, ADMIN1_ID);
        RESTAURANT_MATCHER.assertMatch(actual, ADMIN_1_REST_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class,
                () -> service.get(RESTAURANT_ID, ADMIN2_ID));
    }

    @Test
    void getNotOwn() {
        assertThrows(NotFoundException.class,
                () -> service.get(RESTAURANT_ID, ADMIN2_ID));
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        service.update(updated, ADMIN1_ID);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT_ID, ADMIN1_ID), updated);
    }

    @Test
    void updateNotFound() {
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> service.update(ADMIN_1_REST_1, ADMIN1_ID-5));
        Assertions.assertEquals("Not found entity with [id=" + RESTAURANT_ID + "]", ex.getMessage());
    }

    @Test
    void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), RESTAURANTS);
    }

    @Test
    void createWithException() {
        validateRootCause(() -> service.create(new Restaurant(null, "r1", List.of(), 0), ADMIN1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null, null, List.of(), 300), USER1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null, null, List.of(), 9), USER1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null, null, List.of(), 5001), USER1_ID), ConstraintViolationException.class);
    }
}
