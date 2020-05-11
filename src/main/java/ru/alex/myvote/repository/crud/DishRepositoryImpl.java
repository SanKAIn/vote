package ru.alex.myvote.repository.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.alex.myvote.model.Dish;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.repository.DishRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private CrudDishRepository dishRepository;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Override
    public Dish save(Dish dish, int restId) {
        //Restaurant restaurant = restaurantRepository.getOne(restId);
        dish.setRestaurant(restId);
        if (dish.isNew()){
            dishRepository.saveAndFlush(dish);
            return dish;
        }else if (get(dish.getId(), restId) == null){
            return null;
        }
        return dishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restId) {
        return dishRepository.delete(id, restId) != 0;
    }

    @Override
    public Dish get(int id, int restId) {
        Dish dish = dishRepository.findById(id).orElse(null);
        return dish != null && dish.getRestaurant() == restId ? dish : null;
    }

    @Override
    public List<Dish> getAll(int restId) {
        return dishRepository.getAll(restId);
    }
}
