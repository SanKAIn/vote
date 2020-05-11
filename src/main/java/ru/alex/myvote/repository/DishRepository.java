package ru.alex.myvote.repository;

import ru.alex.myvote.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restId);

    boolean delete(int id, int restId);

    Dish get(int id, int restId);

    List<Dish> getAll(int restId);

}
