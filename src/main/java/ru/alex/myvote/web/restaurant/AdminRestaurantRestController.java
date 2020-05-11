package ru.alex.myvote.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.to.RestaurantTo;
import ru.alex.myvote.web.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.alex.myvote.util.ValidationUtil.handleError;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/admin/restaurants";

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        SecurityUtil.setRestaurant(super.get(id));
        return SecurityUtil.restaurant;
    }

    @Override
    @GetMapping
    public List<RestaurantTo> getAll(){
        return super.getAllAdmin();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id, BindingResult result){
        if (result.hasErrors()) handleError(result);
        super.update(restaurant, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant, BindingResult result){
        if (result.hasErrors()) handleError(result);
        Restaurant created = super.create(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }
}
