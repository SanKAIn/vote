package ru.alex.myvote.repository;

import ru.alex.myvote.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant, int adminId);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id, int adminId);

    Restaurant get(int id, int adminId);

    Restaurant getToUser(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllAdmin(int adminId);
}
