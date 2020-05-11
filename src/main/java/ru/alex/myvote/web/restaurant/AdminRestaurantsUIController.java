package ru.alex.myvote.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.to.RestaurantTo;
import ru.alex.myvote.web.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/restaurants")
public class AdminRestaurantsUIController extends AbstractRestaurantController {

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int id) {
        SecurityUtil.setRestaurant(super.get(id));
        return SecurityUtil.restaurant;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        return super.getAllAdmin();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid Restaurant restaurant) {
        if (restaurant.isNew()) {
            super.create(restaurant);
        }else {
            super.update(restaurant, restaurant.getId());
        }
    }
}
