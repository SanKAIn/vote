package ru.alex.myvote.repository.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.repository.RestaurantRepository;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant, int adminId) {
        restaurant.setAdmin(adminId);
        if (restaurant.isNew()){
            restaurantRepository.saveAndFlush(restaurant);
            return restaurant;
        }else if (get(restaurant.getId(), adminId) == null){
            return null;
        }
        return restaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id, int adminId) {
        return restaurantRepository.delete(id, adminId) != 0;
    }

    @Override
    public Restaurant get(int id, int userId) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        return restaurant != null && restaurant.getAdmin() == userId ? restaurant : null;
    }

    public Restaurant getToUser(int id){
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        return restaurant;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public List<Restaurant> getAllAdmin(int adminId){
        return restaurantRepository.getAllAdmin(adminId);
    }
}
