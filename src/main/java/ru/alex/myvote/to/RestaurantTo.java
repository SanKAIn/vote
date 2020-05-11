package ru.alex.myvote.to;

import ru.alex.myvote.model.Dish;

import java.util.List;

public class RestaurantTo extends BaseTo {
    public Integer id;

    public String name;

    public Dish[] dishes;

    public Integer votes;

    public RestaurantTo() {}

    public RestaurantTo(Integer id, String name, List<Dish> dishes, Integer votes) {
        this.id = id;
        this.name = name;
        this.dishes = dishes.toArray(Dish[]::new);
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Dish[] getDishes() {
        return dishes;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", Name=" + name +
                ", Dishes=" + dishes +
                ", Votes= " + votes +
                '}';
    }
}
