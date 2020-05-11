package ru.alex.myvote.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ru.alex.myvote.model.Dish;
import ru.alex.myvote.repository.DishRepository;
import ru.alex.myvote.web.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

import static ru.alex.myvote.util.ValidationUtil.checkNotFoundWithId;


@RestController
@RequestMapping("ajax/admin/restaurants/")
public class DishUIController {

    private final DishRepository repository;

    public DishUIController(DishRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "{restId}/dishes")
    public List<Dish> getAll(@PathVariable int restId) {
        return repository.getAll(restId);
    }

    @GetMapping(value = "{restId}/dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable int id, @PathVariable int restId){
        return checkNotFoundWithId(repository.get(id, SecurityUtil.restaurant.getId()), id);
    }

    @DeleteMapping(value = "{restId}/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restId){
        checkNotFoundWithId(repository.delete(id, restId), id);
    }

    @PostMapping(value = "{restId}/dishes")
    public void createOrUpdate(@Valid Dish dish, @PathVariable int restId) {
        if (dish.isNew()) {
            Assert.notNull(dish, "dish must not be null");
            repository.save(dish, restId);
        }else {
            Assert.notNull(dish, "dish must not be null");
            checkNotFoundWithId(repository.save(dish, restId), dish.getId());
        }
    }
}
