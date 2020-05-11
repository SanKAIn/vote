package ru.alex.myvote.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.alex.myvote.model.Dish;
import ru.alex.myvote.repository.DishRepository;
import ru.alex.myvote.web.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.alex.myvote.util.ValidationUtil.*;

@RestController
@RequestMapping(value = DishRestController.DISH_REST, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    public static final Logger log = LoggerFactory.getLogger(DishRestController.class);

    static final String DISH_REST = "/rest/admin/restaurants/";

    private final DishRepository repository;

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{restId}/dishes/{id}")
    public Dish get(@PathVariable int id, @PathVariable int restId) {
        log.info("get Dish {} for Restaurant {}", id, restId);
        return repository.get(id, restId);
    }

    @DeleteMapping("{restId}/dishes/{id}")
    public void delete(@PathVariable int id, @PathVariable int restId) {
        log.info("delete Dish {}", id);
        repository.delete(id, restId);
    }

    @GetMapping("{restId}/dishes")
    public List<Dish> getAll(@PathVariable int restId) {
        log.info("getAll dishes for Restaurant {}", restId);
        return repository.getAll(restId);
    }

    @PutMapping(value = "{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid Dish dish, int id, @PathVariable int restId) {
        assureIdConsistent(dish, id);
        log.info("update {} for restaurant {}", dish, restId);
        repository.save(dish, id);
    }

    @PostMapping(value = "{restId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, BindingResult result, @PathVariable int restId) {
        if (result.hasErrors()) handleError(result);
        checkNew(dish);
        log.info("create {} for restaurant {}", dish, SecurityUtil.restaurant.getName());
        Dish created = repository.save(dish, restId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_REST + "{restId}/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }
}