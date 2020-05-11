package ru.alex.myvote.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.repository.RestaurantRepository;

import java.util.List;

import static ru.alex.myvote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id, int adminId){
        return checkNotFoundWithId(repository.get(id, adminId), id);
    }

    public Restaurant getToUser(int id){
        return checkNotFoundWithId(repository.getToUser(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id, int adminId){
        checkNotFoundWithId(repository.delete(id, adminId), id);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public List<Restaurant> getAllAdmin(int adminId){
        return repository.getAllAdmin(adminId);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public void update(Restaurant restaurant, int adminId){
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant, adminId), restaurant.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public void update(Restaurant restaurant){
        repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant, int adminId){
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant, adminId);
    }
}
